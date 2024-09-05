package com.example.springbootretest.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Memo {
    private Long id;
    private String title;
    private String content; // This should match the property used in the template

    public Memo() {
        super();
    }

    public Memo(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
