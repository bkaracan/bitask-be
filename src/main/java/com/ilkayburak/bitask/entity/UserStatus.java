package com.ilkayburak.bitask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_status")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStatus implements Serializable {

    @Id
    @SequenceGenerator(name = "USER_STATUS_ID_GENERATOR", sequenceName = "USER_STATUS_ID_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_STATUS_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "userStatus")
    private List<User> users;

}
