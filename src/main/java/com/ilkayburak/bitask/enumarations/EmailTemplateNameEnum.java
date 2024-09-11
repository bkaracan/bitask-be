package com.ilkayburak.bitask.enumarations;

import lombok.Getter;

@Getter
public enum EmailTemplateNameEnum {
    ACTIVATE_ACCOUNT(1L, "activate_account"),
    RESET_PASSWORD(2L, "reset_password");

    private final Long code;
    private final String name;

    EmailTemplateNameEnum(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EmailTemplateNameEnum getEnumByCode(Long code) {
        for (EmailTemplateNameEnum templateNameEnum : EmailTemplateNameEnum.values()) {
            if (templateNameEnum.code.equals(code)) {
                return templateNameEnum;
            }
        }
        return null;
    }

    public static EmailTemplateNameEnum getEnumByName(String name) {
        for (EmailTemplateNameEnum templateNameEnum : EmailTemplateNameEnum.values()) {
            if (templateNameEnum.name.equals(name)) {
                return templateNameEnum;
            }
        }
        return null;
    }
}
