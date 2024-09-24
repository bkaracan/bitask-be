package com.ilkayburak.bitask;

import com.ilkayburak.bitask.entity.JobTitle;
import com.ilkayburak.bitask.entity.Role;
import com.ilkayburak.bitask.entity.UserStatus;
import com.ilkayburak.bitask.repository.JobTitleRepository;
import com.ilkayburak.bitask.repository.RoleRepository;
import com.ilkayburak.bitask.repository.UserStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class BitaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(
            RoleRepository roleRepository, JobTitleRepository jobTitleRepository, UserStatusRepository userStatusRepository) {
        return args -> {
            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder().name("USER").build());
            }
            List<String> jobTitles = Arrays.asList(
                    "BACKEND DEVELOPER",
                    "FRONTEND DEVELOPER",
                    "BUSINESS ANALYST",
                    "DEVOPS",
                    "Q&A ENGINEER",
                    "SOFTWARE ARCHITECTURE"
            );
            jobTitles.forEach(title -> {
                if (jobTitleRepository.findByName(title).isEmpty()) {
                    jobTitleRepository.save(JobTitle.builder().name(title).build());
                }
            });
            List<String> statuses = Arrays.asList("ONLINE", "AWAY", "BUSY", "OFFLINE");
            statuses.forEach(status -> {
                if (userStatusRepository.findByName(status).isEmpty()) {
                    userStatusRepository.save(UserStatus.builder().name(status).build());
                }
            });
        };
    }
}
