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

    public static TaskPriorityEnum getByCode(Long code) {
        for (TaskPriorityEnum taskPriorityEnum : TaskPriorityEnum.values()) {
            if (taskPriorityEnum.getCode().equals(code)) {
                return taskPriorityEnum;
            }
        }
        return null;
    }

    public static TaskPriorityEnum getByName(String name) {
        for (TaskPriorityEnum taskPriorityEnum : TaskPriorityEnum.values()) {
            if (taskPriorityEnum.getName().equalsIgnoreCase(name)) {
                return taskPriorityEnum;
            }
        }
        return null;
    }
}
