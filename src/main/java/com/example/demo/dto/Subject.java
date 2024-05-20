package com.example.demo.dto;

import lombok.Getter;

import java.util.Set;

@Getter
public class Subject {
    private final Long id;
    private final String email;
    private final String type;

    private Subject(Long id, String email, String type) {
        this.id = id;
        this.email = email;
        this.type = type;
    }

    public static Subject atk(Long id, String email) {
        return new Subject(id, email, "ATK");
    }

    public static Subject rtk(Long id, String email) {
        return new Subject(id, email, "RTK");
    }
}