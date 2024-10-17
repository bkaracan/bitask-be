package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.Board;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByMembers_Id(Long id);
    List<Board> findAllByCreator_Id(Long id);
    Optional<Board> findByNameIgnoreCase(String name);
}
