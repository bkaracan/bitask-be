package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.JobTitleDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.enumarations.JobTitleEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.service.SearchService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchServiceImpl implements SearchService {

    @Override
    public ResponsePayload<JobTitleDTO> getAllJobTitles() {
        List<String> jobTitleList = Arrays.stream(JobTitleEnum.values()).map(JobTitleEnum::getName)
                .collect(Collectors.toList());

        return new ResponsePayload(ResponseEnum.OK, "OK", true, jobTitleList);
    }
}
