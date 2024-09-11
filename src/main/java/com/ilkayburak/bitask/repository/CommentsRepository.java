package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
