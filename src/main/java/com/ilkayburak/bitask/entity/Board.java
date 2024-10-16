package com.ilkayburak.bitask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "board")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @SequenceGenerator(name = "BOARD_ID_GENERATOR", sequenceName = "BOARD_ID_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @ManyToMany
    @JoinTable(name = "board_members", joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

}
