package com.ilkayburak.bitask.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Profile("test")
public class TestMailConfig {

  private static final Logger logger = LoggerFactory.getLogger(TestMailConfig.class);

  @Bean
  public JavaMailSender javaMailSender() {
    return new JavaMailSenderImpl() {
      @Override
      public void send(SimpleMailMessage simpleMessage) {
        // Test sırasında e-posta göndermek yerine loglama yapılır
        logger.info("Fake email send: {}", simpleMessage.getText());
      }

      @Override
      public void send(SimpleMailMessage... simpleMessages) {
        // Test sırasında birden fazla e-posta gönderimi yerine loglama yapılır
        logger.info("Fake email batch send");
      }
    };
  }
}
