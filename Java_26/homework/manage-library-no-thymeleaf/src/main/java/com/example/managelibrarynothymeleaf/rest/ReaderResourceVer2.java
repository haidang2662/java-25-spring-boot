package com.example.managelibrarynothymeleaf.rest;

import com.example.managelibrarynothymeleaf.model.request.SearchReaderRequest;
import com.example.managelibrarynothymeleaf.model.rreponse.CommonSearchResponse;
import com.example.managelibrarynothymeleaf.model.rreponse.ReaderResponse;
import com.example.managelibrarynothymeleaf.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v2/readers")
public class ReaderResourceVer2 {

    ReaderService readerService;

    @GetMapping
    public CommonSearchResponse<ReaderResponse> searchReader(SearchReaderRequest request){
        return readerService.searchReaderVer2(request);
    }

}
