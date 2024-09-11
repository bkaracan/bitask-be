package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.CommentsUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsUsersRepository extends JpaRepository<CommentsUsers, Long> {
}
