package vn.techmaster.danglh.recruitmentproject.repository.custom;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import vn.techmaster.danglh.recruitmentproject.dto.SearchJobDto;
import vn.techmaster.danglh.recruitmentproject.model.request.JobSearchRequest;
import vn.techmaster.danglh.recruitmentproject.repository.BaseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JobCustomRepository extends BaseRepository {

    public List<SearchJobDto> searchJob(JobSearchRequest request) {
        String query = "with raw_data as (\n" +
                "    select id, name, position, " +
                "       year_of_experience_from yearOfExperienceFrom, " +
                "       year_of_experience_to yearOfExperienceTo, " +
                "       working_type workingType, working_time_type workingTimeType, " +
                "       working_address workingAddress, literacy, level, recruiting_quantity recruitingQuantity, " +
                "       expired_date expiredDate, salary_from salaryFrom, salary_to salaryTo, status\n" +
                "    from jobs\n" +
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

        Map<String, Object> parameters = new HashMap<>();
        String searchCondition = "";
        if (request.getName() != null && !request.getName().isBlank()) {
            parameters.put("p_name", "%" + request.getName().toLowerCase() + "%");
            searchCondition += "and lower(name) like :p_name\n";
        }
        if (request.getPosition() != null && !request.getPosition().isBlank()) {
            parameters.put("p_position", "%" + request.getPosition().toLowerCase() + "%");
            searchCondition += "and lower(position) like :p_position\n";
        }
        if (request.getLevel() != null) {
            parameters.put("p_level", request.getLevel().name());
            searchCondition += "and level = :p_level\n";
        }
        if (request.getSkills() != null && !request.getSkills().isBlank()) {
            parameters.put("p_skills", "%" + request.getSkills().toLowerCase() + "%");
            searchCondition += "and lower(name) like :p_skills\n";
        }
        if (request.getExpiredDateFrom() != null) {
            parameters.put("p_expired_date_from", request.getExpiredDateFrom());
            searchCondition += "and expired_date >= :p_expired_date_from\n";
        }
        if (request.getExpiredDateTo() != null) {
            parameters.put("p_expired_date_to", request.getExpiredDateTo());
            searchCondition += "and expired_date <= :p_expired_date_to\n";
        }
        if (request.getStatus() != null) {
            parameters.put("p_status", request.getStatus().name());
            searchCondition += "and status = :p_status\n";
        }

        query = query.replace("{{search_condition}}", searchCondition);
        parameters.put("p_page_size", request.getPageSize());
        parameters.put("p_offset", request.getPageSize() * request.getPageIndex());


        return getNamedParameterJdbcTemplate().query(query, parameters, new BeanPropertyRowMapper<>(SearchJobDto.class));
    }

}
