package vn.techmaster.danglh.recruitmentproject.repository.custom;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import vn.techmaster.danglh.recruitmentproject.constant.Role;
import vn.techmaster.danglh.recruitmentproject.dto.SearchJobDto;
import vn.techmaster.danglh.recruitmentproject.model.request.JobSearchRequest;
import vn.techmaster.danglh.recruitmentproject.repository.BaseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JobCustomRepository extends BaseRepository {

    public List<SearchJobDto> searchJob(JobSearchRequest request, Long creatorId, Role role) {
        String query = "with raw_data as (\n" +
                "    select j.id, j.name, j.position, " +
                "       j.year_of_experience_from yearOfExperienceFrom, " +
                "       j.year_of_experience_to yearOfExperienceTo, " +
                "       j.working_type workingType, j.working_time_type workingTimeType, " +
                "       j.working_address workingAddress, j.literacy, j.level, j.recruiting_quantity recruitingQuantity, " +
                "       j.expired_date expiredDate, j.salary_from salaryFrom, j.salary_to salaryTo, j.status,\n" +
                "       j.created_at createdAt, c.avatar_url companyAvatarUrl, j.urgent\n" +
                "    from jobs j\n" +
                "    left join companies c on j.company_id = c.id\n" +
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

        // nếu role của user hiện tại đang tìm job là company => chỉ show các job của company đấy thôi
        if (role != null && role.equals(Role.COMPANY)) {
            parameters.put("p_company", creatorId);
            searchCondition += "and company_id = :p_company\n";
        }

        // nếu role của user hiện tại đang tìm job là candidate
        //      => show tất cả các job của tất cả các company nhưng chưa hết hạn và đang ở trạng thái PUBLISH
        if (role == null || role.equals(Role.CANDIDATE)) {
//            searchCondition += "and status = 'PUBLISH'\n";
        }

        query = query.replace("{{search_condition}}", searchCondition);
        parameters.put("p_page_size", request.getPageSize());
        parameters.put("p_offset", request.getPageSize() * request.getPageIndex());


        return getNamedParameterJdbcTemplate().query(query, parameters, new BeanPropertyRowMapper<>(SearchJobDto.class));
    }

}
