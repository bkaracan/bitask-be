package com.ilkayburak.bitask.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

    BACKLOG(1L, "Backlog"),
    TO_DO(2L, "To Do"),
    IN_PROGRESS(3L, "In Progress"),
    DONE(4L, "Done"),
    BLOCKED(5L, "Blocked"),
    ON_HOLD(6L, "On Hold"),
    CANCELLED(7L, "Cancelled"),
    REVIEW(8L, "Review"),
    COMPLETED(9L, "Completed");

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
