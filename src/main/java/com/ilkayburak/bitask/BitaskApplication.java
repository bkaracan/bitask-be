package com.ilkayburak.bitask;

import com.ilkayburak.bitask.entity.JobTitle;
import com.ilkayburak.bitask.entity.Role;
import com.ilkayburak.bitask.repository.JobTitleRepository;
import com.ilkayburak.bitask.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class BitaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(BitaskApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(RoleRepository roleRepository, JobTitleRepository jobTitleRepository) {
    return args -> {
      if (roleRepository.findByName("USER").isEmpty()) {
        roleRepository.save(Role.builder().name("USER").build());
      }
      if (jobTitleRepository.findByName("BACKEND DEVELOPER").isEmpty()) {
        jobTitleRepository.save(JobTitle.builder().name("BACKEND DEVELOPER").build());
      }
      if (jobTitleRepository.findByName("FRONTEND DEVELOPER").isEmpty()) {
        jobTitleRepository.save(JobTitle.builder().name("FRONTEND DEVELOPER").build());
      }
      if (jobTitleRepository.findByName("BUSINESS ANALYST").isEmpty()) {
        jobTitleRepository.save(JobTitle.builder().name("BUSINESS ANALYST").build());
      }
      if (jobTitleRepository.findByName("DEVOPS").isEmpty()) {
        jobTitleRepository.save(JobTitle.builder().name("DEVOPS").build());
      }
      if (jobTitleRepository.findByName("Q&A ENGINEER").isEmpty()) {
        jobTitleRepository.save(JobTitle.builder().name("Q&A ENGINEER").build());
      }
      if (jobTitleRepository.findByName("SOFTWARE ARCHITECTURE").isEmpty()) {
        jobTitleRepository.save(JobTitle.builder().name("SOFTWARE ARCHITECTURE").build());
      }
    };
  }
}
