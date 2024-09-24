package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/getAllJobTitles")
    public ResponsePayload<List<String>> getAllJobTitles() {
        return searchService.getAllJobTitles();
    }

    @GetMapping("/getAllUserStatus")
    public ResponsePayload<List<String>> getAllUserStatus() {
        return searchService.getAllUserStatus();
    }

}
