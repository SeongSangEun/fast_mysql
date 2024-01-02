package com.example.fastcampusmysql.domain.post.repo;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private static final String TABLE = "post";
    private final static RowMapper<DailyPostCount> DAILY_POST_COUNT_MAPPER = (ResultSet resultSet, int rowNum) ->
            new DailyPostCount(
                    resultSet.getLong("memberId"),
                    resultSet.getObject("createdDate", LocalDate.class),
                    resultSet.getLong("count")
            );

    public Post save(Post post) {
        if(post.getId() == null) {
            return insert(post);
        } else {
            throw new UnsupportedOperationException("Post 는 갱신을 지원하지 않습니다.");
        }

    }

    private Post insert(Post post) {
        // NamedParameterJdbcTemplate 내에 있는 keyHolder를 이용하여 구현도 가능하나, 다른방법으로 구현함
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Post.builder()
                .id(id)
                .memberId(post.getMemberId())
                .contents(post.getContents())
                .createdDate(post.getCreatedDate())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request) {

        var sql = String.format("select created_date, member_id, count(id) as count " +
                "from %s " +
                "where member_id = :memberId and created_date between :firstDate and :lastDate " +
                "group by created_date, member_id", TABLE);
        var params = new BeanPropertySqlParameterSource(request);
        return namedJdbcTemplate.query(sql, params, DAILY_POST_COUNT_MAPPER);
    }

}
