package com.ilkayburak.bitask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "job_title")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobTitle {

  @Id
  @SequenceGenerator(name = "JOB_TITLE_ID_GENERATOR", sequenceName = "JOB_TITLE_ID_GEN", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_TITLE_ID_GENERATOR")
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "jobTitle")
  private List<User> users;
}
