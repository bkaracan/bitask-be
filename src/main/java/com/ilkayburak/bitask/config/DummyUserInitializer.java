package com.ilkayburak.bitask.config;

import com.ilkayburak.bitask.entity.Role;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.repository.JobTitleRepository;
import com.ilkayburak.bitask.repository.RoleRepository;
import com.ilkayburak.bitask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DummyUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JobTitleRepository jobTitleRepository;

    @Override
    public void run(String... args) {
        Role userRole = roleRepository.findByName("USER").orElseThrow();
        String password = passwordEncoder.encode(UUID.randomUUID().toString());

        String dioMail = "diobarando@domain.com";
        if (userRepository.existsByEmail(dioMail)) {
            userRepository.delete(userRepository.findByEmail(dioMail).orElseThrow());
            User dio = User.builder()
                    .firstName("Dio")
                    .lastName("Barando")
                    .email(dioMail)
                    .password(password)
                    .dateOfBirth(LocalDate.of(1868, 1, 1))
                    .jobTitle(jobTitleRepository.findById(1L).orElseThrow())
                    .isEnabled(true)
                    .isAccountLocked(false)
                    .roles(List.of(userRole))
                    .build();

            userRepository.save(dio);
        }

        String aragornMail = "dunedainaragorn@domain.com";
        if (userRepository.existsByEmail(aragornMail)) {
            userRepository.delete(userRepository.findByEmail(aragornMail).orElseThrow());
        }
        User aragorn = User.builder()
                .firstName("Aragorn")
                .lastName("Dunedain")
                .email(aragornMail)
                .password(password)
                .dateOfBirth(LocalDate.of(2931, 3, 1))
                .jobTitle(jobTitleRepository.findById(2L).orElseThrow())
                .isEnabled(true)
                .isAccountLocked(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(aragorn);

        String tonyMail = "tonystark@domain.com";
        if (userRepository.existsByEmail(tonyMail)) {
            userRepository.delete(userRepository.findByEmail(tonyMail).orElseThrow());
        }
        User tony = User.builder()
                .firstName("Tony")
                .lastName("Stark")
                .email("tonystark@domain.com")
                .password(password)
                .dateOfBirth(LocalDate.of(1970, 5, 29))
                .jobTitle(jobTitleRepository.findById(6L).orElseThrow())
                .isEnabled(true)
                .isAccountLocked(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(tony);
    }
}
