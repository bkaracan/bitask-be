package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.enumarations.JobTitleEnum;
import com.ilkayburak.bitask.enumarations.UserStatusEnum;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.service.SearchService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchServiceImpl implements SearchService {

    @Override
    public ResponsePayload<List<String>> getAllJobTitles() {

        List<String> jobTitleList = Arrays.stream(JobTitleEnum.values()).map(JobTitleEnum::getName)
                .toList();

        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.RETRIEVE_SUCCESS.getMessage(), true, jobTitleList);
    }

    @Override
    public ResponsePayload<List<String>> getAllUserStatus() {
        List<String> userStatusList = Arrays.stream(UserStatusEnum.values()).map(UserStatusEnum::getName)
            .toList();
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.RETRIEVE_SUCCESS.getMessage(), true, userStatusList);
    }
}
