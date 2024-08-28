package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.JobTitleDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;

public interface SearchService {

    ResponsePayload<JobTitleDTO> getAllJobTitles();

}
