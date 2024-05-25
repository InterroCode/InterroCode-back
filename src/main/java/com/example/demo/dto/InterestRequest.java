package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestRequest {

    private String email;
    private String task;
    private String mainLanguage;
    private String subLanguage;
    private String mainFramework;
    private String subFramework;

    @Builder
    public InterestRequest(String email, String task, String mainLanguage, String subLanguage, String mainFramework, String subFramework) {
        this.email = email;
        this.task = task;
        this.mainLanguage = mainLanguage;
        this.subLanguage = subLanguage;
        this.mainFramework = mainFramework;
        this.subFramework = subFramework;
    }
}
