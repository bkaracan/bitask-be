package com.ilkayburak.bitask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @SequenceGenerator(name = "TASK_ID_GENERATOR", sequenceName = "TASK_ID_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    private String keyId;

    private String title;
    private String description;
    private Integer priority;

    private Long status;
    private Long type;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    private LocalDate expectedFinishDate;
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToMany
    @JoinTable(name = "task_assignees", joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> assignees;

    private Long blockingTask;

}
