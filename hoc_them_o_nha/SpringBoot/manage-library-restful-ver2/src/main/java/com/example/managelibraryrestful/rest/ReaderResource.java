package com.example.managelibraryrestful.rest;

import com.example.managelibraryrestful.exceptionhandling.exception.ObjectNotFoundException;
import com.example.managelibraryrestful.model.request.ReaderRequest;
import com.example.managelibraryrestful.model.response.ReaderResponse;
import com.example.managelibraryrestful.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/readers")
@AllArgsConstructor
public class ReaderResource {

    ReaderService readerService;

    @GetMapping
    public List<ReaderResponse> getReaders() {
        return readerService.getReaders();
    }

    @GetMapping("/{id}")
    public ReaderResponse getReaderDetail(@PathVariable("id") Long idReader) throws ObjectNotFoundException {
        return readerService.getReaderDetail(idReader);
    }

    @Operation(summary = "API tạo mới bạn đọc", description = "Mô tả cho API tạo mới, mục đích, ý nghĩa, ...")
    @PostMapping
    public ReaderResponse creatReader(@RequestBody @Valid ReaderRequest request) {
        return readerService.creatReader(request);
    }

    @PutMapping("/{id}")
    public ReaderResponse updateReader(@PathVariable("id") Long idReader, @RequestBody ReaderRequest request) throws ObjectNotFoundException {
        return readerService.updateReader(idReader, request);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable("id") Long idReader) throws ObjectNotFoundException {
        readerService.deleteReader(idReader);
    }
}

