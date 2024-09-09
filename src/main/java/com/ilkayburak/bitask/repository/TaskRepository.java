package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByBoardId(Long boardId);
    List<Task> findAllByBoardIdAndStatus(Long boardId, Long status);

}
