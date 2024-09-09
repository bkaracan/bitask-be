package com.ilkayburak.bitask.enumarations.core;

import lombok.Getter;

@Getter
public enum MessageEnum {

    NOT_FOUND(1, "The record is not found!"),
    NOT_AUTH(2, "You are not authorized for this process!"),
    UNEXPECTED_ERROR(3, "UNEXPECTED ERROR!"),
    UPDATE_SUCCESS(4, "Record is updated successfully!"),
    SAVE_SUCCESS(5, "Record is saved successfully!"),
    DELETE_SUCCESS(6, "Record is deleted successfully!"),
    RECORD_EXISTS(7, "Record already exists!"),
    EMPTY_LIST(8, "List is Empty!"),
    AUTHENTICATED(9, "The user has been authenticated!"),
    REGISTRATION_SUCCESS(10, "The registration has been completed!" +
            " Please check your email to activate your account."),
    ACTIVATION_SUCCESS(11, "The activation has been completed!"),
    INCORRECT_CURRENT_PASSWORD(12, "Current password is incorrect!"),
    NEW_PASSWORD_DOES_NOT_MATCH(13, "New password does not match!"),
    ACCOUNT_LOCKED(14, "User account is locked!"),
    ACCOUNT_DISABLED(15, "User account is disabled!"),
    BAD_CREDENTIALS(16, "Email and/or password is incorrect!"),
    RETRIEVE_SUCCESS(17, "Data retrieved successfully!");



    private final Integer kod;
    private final String message;

    MessageEnum(Integer kod, String message) {
        this.kod = kod;
        this.message = message;
    }
}
