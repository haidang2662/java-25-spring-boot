package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PageResponseIplm <T> implements PageResponse{

    int currentPage;
    int pageSize;
    List<T> data;

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int totalElements() {
        return data.size();
    }

    @Override
    public int totalPages() {
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    @Override
    public List getContent() {
        int fromIndex = (currentPage - 1) * pageSize; // from index : là vị trí bắt đầu
        if (fromIndex >= data.size()) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + pageSize , data.size());
        return data.subList(fromIndex , toIndex);
    }
}
