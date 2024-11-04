package com.example.managelibrarynothymeleaf.mapper;

import com.example.managelibrarynothymeleaf.dto.SearchReaderDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchReaderMapper extends BeanPropertyRowMapper<SearchReaderDto> {

    @Override
    public SearchReaderDto mapRow(ResultSet rs , int rowNumber) throws SQLException{
        return SearchReaderDto.builder()
                .id(rs.getLong("id"))
                .address(rs.getString("address"))
                .dob(rs.getDate("dob").toLocalDate())
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .phone(rs.getString("phone"))
                .totalRecord(rs.getLong("totalRecord"))
                .build();
    }
}
