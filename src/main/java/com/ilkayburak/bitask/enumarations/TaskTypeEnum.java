package com.ilkayburak.bitask.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    BACKEND(1L, "Backend"),
    FRONTEND(2L, "Frontend"),
    DEVOPS(3L, "Devops"),
    QA(4L, "QA"),
    DESIGN(5L, "Design"),
    DOCUMENTATION(6L, "Documentation");

    private final Long code;
    private final String name;

    public static TaskTypeEnum getByCode(final Long code) {
        for (final TaskTypeEnum taskTypeEnum : TaskTypeEnum.values()) {
            if (taskTypeEnum.code == code) {
                return taskTypeEnum;
            }
        }
        return null;
    }

    public static TaskTypeEnum getByName(final String name) {
        for (final TaskTypeEnum taskTypeEnum : TaskTypeEnum.values()) {
            if (taskTypeEnum.name.equalsIgnoreCase(name)) {
                return taskTypeEnum;
            }
        }
        return null;
    }
}
