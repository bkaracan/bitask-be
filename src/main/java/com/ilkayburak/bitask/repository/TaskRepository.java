package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByKeyIdAndBoardId(String keyId, Long boardId);
    List<Task> findAllByBoard_Id(Long boardId);
    List<Task> findAllByBoard_IdAndStatus(Long boardId, Long status);
    List<Task> findAllByStatusAndAssignees_Id(Long status, Long assigneeId);


}
