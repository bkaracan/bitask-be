package com.ilkayburak.bitask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sprint")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Sprint {

    @Id
    @SequenceGenerator(name = "SPRINT_ID_GENERATOR", sequenceName = "SPRINT_ID_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPRINT_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private Long status;

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

}
