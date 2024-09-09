package com.ilkayburak.bitask.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskPriorityEnum {

    LOW(1L, "Low"),
    MEDIUM(2L, "Medium"),
    HIGH(3L, "High");

    private final Long code;
    private final String name;

    public static TaskStatusEnum getByCode(Long code) {
        for (TaskStatusEnum taskStatusEnum : TaskStatusEnum.values()) {
            if (taskStatusEnum.getCode().equals(code)) {
                return taskStatusEnum;
            }
        }
        return null;
    }

    public static TaskStatusEnum getByName(String name) {
        for (TaskStatusEnum taskStatusEnum : TaskStatusEnum.values()) {
            if (taskStatusEnum.getName().equalsIgnoreCase(name)) {
                return taskStatusEnum;
            }
        }
        return null;
    }
}
