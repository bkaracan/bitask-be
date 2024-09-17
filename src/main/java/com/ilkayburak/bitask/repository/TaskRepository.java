package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByKeyIdAndBoardId(String keyId, Long boardId);
    List<Task> findAllByBoardId(Long boardId);
    List<Task> findAllByBoardIdAndStatus(Long boardId, Long status);
    List<Task> findAllByStatusAndAssignees_Id(Long status, Long assigneeId);


}
