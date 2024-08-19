package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvFileReader implements IFileReader {

    @Override
    public List<Book> readFile(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(fileReader)
                    .withType(Book.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
