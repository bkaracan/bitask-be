package com.ilkayburak.bitask.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    ONLINE(1L, "Online"),
    AWAY(2L, "Away"),
    BUSY(3L, "Busy"),
    OFFLINE(4L, "Offline");

    private final Long code;
    private final String name;


    public static UserStatusEnum getByCode(Long code) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (userStatusEnum.getCode().equals(code)) {
                return userStatusEnum;
            }
        }
        return null;
    }

    public static UserStatusEnum getByName(String name) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (userStatusEnum.getName().equalsIgnoreCase(name)) {
                return userStatusEnum;
            }
        }
        return null;
    }
}
