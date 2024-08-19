package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.JobTitle;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

  Optional<JobTitle> findByName(String jobTitleName);
}
