package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.SprintDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.Sprint;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.SprintDTOMapper;
import com.ilkayburak.bitask.repository.SprintRepository;
import com.ilkayburak.bitask.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final SprintDTOMapper mapper;

    @Override
    public ResponsePayload<SprintDTO> save(SprintDTO sprintDTO) {
        Optional<Sprint> sprint = sprintRepository.findById(sprintDTO.getId());
        if (sprint.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, "Sprint created successfully.",
                    mapper.convertToDto(sprintRepository.save(mapper.convertToEntity(sprintDTO))));
        }
        return new ResponsePayload<>(ResponseEnum.OK, "Sprint updated successfully.",
                mapper.convertToDto(sprintRepository.save(mapper.convertToEntity(sprintDTO))));
    }

    @Override
    public ResponsePayload<SprintDTO> getById(Long id) {
        Optional<Sprint> sprint = sprintRepository.findById(id);
        if (sprint.isPresent()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto(sprint.get()));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    @Override
    public ResponsePayload<Void> deleteById(Long id) {
        Optional<Sprint> sprint = sprintRepository.findById(id);
        if (sprint.isPresent()) {
            sprintRepository.deleteById(id);
            return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.DELETE_SUCCESS.getMessage());
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }
}
