package com.ilkayburak.bitask.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobTitleEnum {

    BACKEND_DEVELOPER(1L, "Backend Developer"),
    FRONTEND_DEVEOPER(2L, "Frontend Developer"),
    BUSINESS_ANALYST(3L, "Business Analysit"),
    DEVOPS(4L, "Devops"),
    QA_ENGINEER(5L, "Q&A Engineer"),
    SOFTWARE_ARCHITECTURE(6L, "Software Architecture");

    private final Long code;
    private final String name;

    public static JobTitleEnum getByCode(Long code) {
        for (JobTitleEnum e : JobTitleEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public static JobTitleEnum getByName(String name) {
        for (JobTitleEnum e : JobTitleEnum.values()) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
