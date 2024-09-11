package com.ilkayburak.bitask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "comments_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentsUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "COMMENTS_USERS_ID_GENERATOR", sequenceName = "COMMENTS_USERS_ID_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_USERS_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comments comment;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tagged_user")
    private User taggedUser;

}
