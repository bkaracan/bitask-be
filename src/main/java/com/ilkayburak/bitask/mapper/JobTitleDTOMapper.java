package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.JobTitleDTO;
import com.ilkayburak.bitask.entity.JobTitle;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JobTitleDTOMapper {

  public JobTitleDTO convertToDto(JobTitle jobTitle) {
    if (jobTitle == null) {
      return null;
    }
    return JobTitleDTO.builder()
        .id(jobTitle.getId())
        .name(jobTitle.getName())
        .build();
  }

  public JobTitle convertToEntity(JobTitleDTO jobTitleDTO) {
    if (jobTitleDTO == null) {
      return null;
    }
    return JobTitle.builder()
        .id(jobTitleDTO.getId())
        .name(jobTitleDTO.getName())
        .build();
  }

  public List<JobTitleDTO> mapList(List<JobTitle> jobTitles) {
    return jobTitles.stream()
        .map(this::convertToDto)
        .toList();
  }

  public List<JobTitle> convertListToEntity(List<JobTitleDTO> jobTitleDTOs) {
    return jobTitleDTOs.stream()
        .map(this::convertToEntity)
        .toList();
  }

  public JobTitleDTO mapWithoutObjects(JobTitle jobTitle) {
    if (jobTitle == null) {
      return null;
    }
    return JobTitleDTO.builder()
        .id(jobTitle.getId())
        .name(jobTitle.getName())
        .build();
  }

  public JobTitleDTO mapWithObjects(JobTitle jobTitle) {
    if (jobTitle == null) {
      return null;
    }
    return JobTitleDTO.builder()
        .id(jobTitle.getId())
        .name(jobTitle.getName())
        .users(new UserDTOMapper().mapListWithoutObjects(jobTitle.getUsers()))
        .build();
  }

  public List<JobTitleDTO> mapListWithoutObjects(List<JobTitle> jobTitles) {
    return jobTitles.stream()
        .map(this::mapWithoutObjects)
        .toList();
  }

  public List<JobTitleDTO> mapListWithObjects(List<JobTitle> jobTitles) {
    return jobTitles.stream()
        .map(this::mapWithObjects)
        .toList();
  }
}
