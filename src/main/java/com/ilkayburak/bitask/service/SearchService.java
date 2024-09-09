package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.JobTitleDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import java.util.List;

public interface SearchService {

    ResponsePayload<List<String>> getAllJobTitles();

}
