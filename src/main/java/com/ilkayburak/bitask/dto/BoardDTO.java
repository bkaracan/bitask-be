package com.ilkayburak.bitask.dto;

import com.ilkayburak.bitask.entity.Task;
import com.ilkayburak.bitask.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class BoardDTO {

    private Long id;
    private String name;
    private String uniqueId;
    private LocalDateTime createDate;
    private UserDTO creator;
    private List<UserDTO> members;
    private List<TaskDTO> tasks;

    public static List<Long> getMembersByIdList(List<User> users) {
        List<Long> members = new ArrayList<>();
        for (User user : users) {
            members.add(user.getId());
        }
        return members;
    }

    public static List<Long> getTasksByIdList(List<Task> tasks) {
        List<Long> members = new ArrayList<>();
        for (Task task : tasks) {
            members.add(task.getId());
        }
        return members;
    }
}
