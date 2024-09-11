package com.ilkayburak.bitask.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SprintStatusEnum {

    OPEN(1L, "Open"),
    IN_PROGRESS(2L, "In progress"),
    COMPLETED(3L, "Completed"),
    CANCELLED(4L, "Cancelled");

    private final Long code;
    private final String name;

    public static SprintStatusEnum getByCode(Long code) {
        for (SprintStatusEnum status : SprintStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    public static SprintStatusEnum getByName(String name) {
        for (SprintStatusEnum status : SprintStatusEnum.values()) {
            if (status.getName().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }

}
