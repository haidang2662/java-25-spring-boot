package com.example.managelibraryrestful.service;

import com.example.managelibraryrestful.entity.Reader;
import com.example.managelibraryrestful.exceptionhandling.exception.ObjectNotFoundException;
import com.example.managelibraryrestful.model.request.ReaderRequest;
import com.example.managelibraryrestful.model.response.ReaderResponse;
import com.example.managelibraryrestful.repository.ReaderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReaderService {

    ReaderRepository readerRepository;

    ObjectMapper objectMapper;

    public List<ReaderResponse> getReaders() {

        List<Reader> readers = readerRepository.findAll();
        return readers
                .stream()
                .map(s -> objectMapper.convertValue(s, ReaderResponse.class))
                .toList();
    }

    public ReaderResponse getReaderDetail(Long idReader) throws ObjectNotFoundException {
        Optional<Reader> readerOptional = readerRepository.findById(idReader);
        if (readerOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy bạn đọc có id " + idReader);
        }

        Reader reader = readerOptional.get();
        return objectMapper.convertValue(reader, ReaderResponse.class);
    }

    public ReaderResponse creatReader(ReaderRequest request) {
        Reader reader = objectMapper.convertValue(request, Reader.class);
        readerRepository.save(reader);
        return objectMapper.convertValue(reader, ReaderResponse.class);
    }

    public ReaderResponse updateReader(Long idReader, ReaderRequest request) throws ObjectNotFoundException {
        Optional<Reader> readerOptional = readerRepository.findById(idReader);
        if (readerOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không có bạn đọc có id " + idReader);
        }

        Reader reader = readerOptional.get();
        reader.setName(request.getName());
        reader.setAddress(request.getAddress());
        reader.setDob(request.getDob());
        reader.setEmail(request.getEmail());
        reader.setPhone(request.getPhone());
        readerRepository.save(reader);
        return objectMapper.convertValue(reader, ReaderResponse.class);
    }

    public void deleteReader(Long idReader) throws ObjectNotFoundException {
        Optional<Reader> readerOptional = readerRepository.findById(idReader);
        if (readerOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy bạn đọc có id " + idReader);
        }
        readerRepository.deleteById(idReader);
    }
}
