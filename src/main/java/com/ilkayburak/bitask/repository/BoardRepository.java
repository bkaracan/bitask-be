package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
