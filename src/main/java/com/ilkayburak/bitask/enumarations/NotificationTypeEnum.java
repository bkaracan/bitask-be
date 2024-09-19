package com.ilkayburak.bitask.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationTypeEnum {

    TASK_ASSIGNED(1L, "Task assigned"),
    TASK_DONE(2L, "Task done"),
    TASK_BLOCKED(3L, "Task blocked"),
    TASK_ON_HOLD(4L, "Task on hold"),
    TASK_CANCELLED(5L, "Task cancelled"),
    TASK_IN_REVIEW(6L, "Task in review"),
    TASK_COMPLETED(7L, "Task completed"),
    COMMENT_ADDED(8L, "Comment added"),
    SPRINT_STARTED(9L, "Sprint started"),
    SPRINT_FINISHED(10L, "Sprint finished");

    private final Long code;
    private final String name;

    public static NotificationTypeEnum getByCode(Long code) {
        for (NotificationTypeEnum e : NotificationTypeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public static NotificationTypeEnum getByName(String name) {
        for (NotificationTypeEnum e : NotificationTypeEnum.values()) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
