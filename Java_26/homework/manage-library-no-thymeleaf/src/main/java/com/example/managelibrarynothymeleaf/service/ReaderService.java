package com.example.managelibrarynothymeleaf.service;

import com.example.managelibrarynothymeleaf.dto.SearchReaderDto;
import com.example.managelibrarynothymeleaf.entity.Reader;
import com.example.managelibrarynothymeleaf.model.request.SearchReaderRequest;
import com.example.managelibrarynothymeleaf.model.rreponse.CommonSearchResponse;
import com.example.managelibrarynothymeleaf.model.rreponse.ReaderResponse;
import com.example.managelibrarynothymeleaf.repository.ReaderRepository;
import com.example.managelibrarynothymeleaf.repository.custom.ReaderCustomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {

    ObjectMapper objectMapper;
    ReaderRepository readerRepository;
    ReaderCustomRepository readerCustomRepository;

    public CommonSearchResponse<ReaderResponse> searchReader(SearchReaderRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());

        Page<Reader> readerPages = readerRepository.findByNameContainingIgnoreCase(request.getName(), pageable);

        List<ReaderResponse> readerResponses = readerPages
                .map(s -> objectMapper.convertValue(s, ReaderResponse.class))
                .toList();

        return CommonSearchResponse.<ReaderResponse>builder()
                .totalRecord(readerPages.getTotalElements())
                .totalPage(readerPages.getTotalPages())
                .data(readerResponses)
                .paging(new CommonSearchResponse.CommonSearchPageInfo(request.getPageSize(), request.getPageIndex()))
                .build();
    }

    public CommonSearchResponse<ReaderResponse> searchReaderVer2(SearchReaderRequest request) {
        List<SearchReaderDto> result = readerCustomRepository.searchReader(request);
        Long totalRecord = 0l;
        List<ReaderResponse> readerResponses = new ArrayList<>();
        if (!result.isEmpty()) {
            totalRecord = result.get(0).getTotalRecord();
            readerResponses = result
                    .stream()
                    .map(s -> objectMapper.convertValue(s, ReaderResponse.class))
                    .toList();
        }

        int totalPage = (int) Math.ceil((double) totalRecord / request.getPageSize());

        return CommonSearchResponse.<ReaderResponse>builder()
                .totalRecord(totalRecord)
                .totalPage(totalPage)
                .data(readerResponses)
                .paging(new CommonSearchResponse.CommonSearchPageInfo(request.getPageSize(), request.getPageIndex()))
                .build();
    }
}
