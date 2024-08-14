package com.ilkayburak.bitask.email;

import com.ilkayburak.bitask.enumarations.EmailTemplateNameEnum;
import jakarta.mail.MessagingException;

public interface EmailService {

    public void sendEmail(String to, String username, EmailTemplateNameEnum emailTemplateName,
                          String confirmationUrl, String activationCode, String subject) throws MessagingException;
}
