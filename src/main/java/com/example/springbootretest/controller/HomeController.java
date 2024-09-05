package com.example.springbootretest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String home(Model model) {
		System.out.println("GET - /home 호출 ...");

		model.addAttribute("message", "Welcome to spring boot home.");
		model.addAttribute("todoMessage", "See Todo Stuff.");
		// 뷰네임 리턴
		// src/main/resources/templates/home.html
		return "home";
	}

}
