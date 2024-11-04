package com.example.managelibrarynothymeleaf.repository.custom;

import com.example.managelibrarynothymeleaf.dto.SearchReaderDto;
import com.example.managelibrarynothymeleaf.mapper.SearchReaderMapper;
import com.example.managelibrarynothymeleaf.model.request.SearchReaderRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReaderCustomRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<SearchReaderDto> searchReader(SearchReaderRequest request){
        String query = "with raw_data as (\n" +
                "    select id, address, dob, email, name, phone\n" +
                "    from readers\n" +
                "    where 1 = 1\n" +
                "   {{search_condition}}\n" +
                "), count_data as(\n" +
                "    select count(*) totalRecord\n" +
                "    from raw_data\n" +
                ")\n" +
                "select r.*, c.totalRecord\n" +
                "from raw_data r, count_data c\n" +
                "limit :p_page_size\n" +
                "offset :p_offset";

        Map<String , Object> parameters = new HashMap<>();
        String searchCondition = "";
        if (request.getName() != null && !request.getName().isBlank()) {
            parameters.put("p_name", "%" + request.getName().toLowerCase() + "%");
            searchCondition += "and lower(name) like :p_name\n";
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            parameters.put("p_email", "%" + request.getEmail().toLowerCase() + "%");
            searchCondition += "and lower(email) like :p_email\n";
        }
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            parameters.put("p_phone", "%" + request.getPhone().toLowerCase() + "%");
            searchCondition += "and lower(phone) like :p_phone\n";
        }
        if (request.getAddress() != null && !request.getAddress().isBlank()) {
            parameters.put("p_address", "%" + request.getAddress().toLowerCase() + "%");
            searchCondition += "and lower(address) like :p_address\n";
        }
        query = query.replace("{{search_condition}}", searchCondition);
        parameters.put("p_page_size", request.getPageSize());
        parameters.put("p_offset", request.getPageSize() * request.getPageIndex());

        return namedParameterJdbcTemplate.query(query, parameters, new SearchReaderMapper());
    };

}
