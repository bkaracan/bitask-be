package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.SprintDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;

public interface SprintService {

    ResponsePayload<SprintDTO> save(SprintDTO sprintDTO);
    ResponsePayload<SprintDTO> getById(Long id);
    ResponsePayload<Void> deleteById(Long id);

}
