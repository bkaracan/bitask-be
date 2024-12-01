package com.ilkayburak.bitask;

import com.ilkayburak.bitask.entity.JobTitle;
import com.ilkayburak.bitask.entity.Role;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.entity.UserStatus;
import com.ilkayburak.bitask.repository.JobTitleRepository;
import com.ilkayburak.bitask.repository.RoleRepository;
import com.ilkayburak.bitask.repository.UserRepository;
import com.ilkayburak.bitask.repository.UserStatusRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@RequiredArgsConstructor
public class BitaskApplication {

    private static final Logger logger = LoggerFactory.getLogger(BitaskApplication.class);

    private final RoleRepository roleRepository;
    private final JobTitleRepository jobTitleRepository;
    private final UserStatusRepository userStatusRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BitaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            logger.info("Starting data initialization...");

            initializeRoles();
            initializeJobTitles();
            initializeUserStatuses();
            initializeDummyUsers();

            logger.info("Data initialization completed.");
        };
    }

    private void initializeRoles() {
        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(Role.builder().name("USER").build());
            logger.info("Role 'USER' created.");
        }
    }

    private void initializeJobTitles() {
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
                logger.info("Job title '{}' created.", title);
            }
        });
    }

    private void initializeUserStatuses() {
        List<String> statuses = Arrays.asList("ONLINE", "AWAY", "BUSY", "OFFLINE");
        statuses.forEach(status -> {
            if (userStatusRepository.findByName(status).isEmpty()) {
                userStatusRepository.save(UserStatus.builder().name(status).build());
                logger.info("User status '{}' created.", status);
            }
        });
    }

    private void initializeDummyUsers() {
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role 'USER' not found"));

        String randomPassword = passwordEncoder.encode(UUID.randomUUID().toString());

        createUser("Dio", "Brando", "diobarando@domain.com", LocalDate.of(1868, 1, 1), 1L, userRole, randomPassword);
        createUser("Aragorn", "Dunedain", "dunedainaragorn@domain.com", LocalDate.of(2931, 3, 1), 2L, userRole, randomPassword);
        createUser("Tony", "Stark", "tonystark@domain.com", LocalDate.of(1970, 5, 29), 6L, userRole, randomPassword);
    }

    private void createUser(String firstName, String lastName, String email, LocalDate dob, Long jobId, Role role, String password) {
        if (userRepository.existsByEmail(email)) {
            userRepository.delete(userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User not found to delete")));
        }
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .dateOfBirth(dob)
                .jobTitle(jobTitleRepository.findById(jobId).orElseThrow(() -> new IllegalStateException("Job title not found")))
                .isEnabled(true)
                .isAccountLocked(false)
                .roles(List.of(role))
                .build();
        userRepository.save(user);
        logger.info("User '{}' {} created with email: {}", firstName, lastName, email);
    }
}
