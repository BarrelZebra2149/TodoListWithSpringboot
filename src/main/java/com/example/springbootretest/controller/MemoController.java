package com.example.springbootretest.controller;

import com.example.springbootretest.Model.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/memos")
public class MemoController {
    static List<Memo> memos = new ArrayList<>();

    static {
        memos.add(Memo.builder().id(1L).title("title").content("description").build());
        memos.add(Memo.builder().id(2L).title("new title").content("new description").build());
        memos.add(Memo.builder().id(3L).title("other title").content("new other description").build());
    }

    @GetMapping
    public String memos(Model model) {
        model.addAttribute("memos", memos);
        return "todo";
    }

    @GetMapping("/create")
    public String createMemoForm(Model model) {
        model.addAttribute("memo", new Memo()); // Create an empty Memo object for the form
        return "todoCreate";
    }

    @PostMapping("/create")
    public String createMemo(@ModelAttribute Memo memo) {
        // Assign a new ID (in a real application, you would probably use a database to handle IDs)
        Long newId = memos.stream().mapToLong(Memo::getId).max().orElse(0) + 1;
        memo.setId(newId);
        memos.add(memo);
        return "redirect:/memos";
    }

    @GetMapping("/edit/{id}")
    public String editMemo(Model model, @PathVariable Long id) {
        Optional<Memo> memoOpt = memos.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (memoOpt.isPresent()) {
            model.addAttribute("memo", memoOpt.get());
            return "todoEdit";
        } else {
            return "redirect:/memos";
        }
    }

    @PostMapping("/edit")
    public String updateMemo(@ModelAttribute Memo memo) {
        for (int i = 0; i < memos.size(); i++) {
            if (memos.get(i).getId().equals(memo.getId())) {
                memos.set(i, memo);
                break;
            }
        }
        return "redirect:/memos";
    }

    @PostMapping("/delete/{id}")
    public String deleteMemo(@PathVariable Long id) {
        memos.removeIf(m -> m.getId().equals(id));
        return "redirect:/memos";
    }
}
