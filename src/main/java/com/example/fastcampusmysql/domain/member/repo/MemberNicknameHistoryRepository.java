package com.example.fastcampusmysql.domain.member.repo;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberNicknameHistoryRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private static final String TABLE = "member_nickname_history";

    public MemberNicknameHistory save(MemberNicknameHistory history) {
        /*
            member id를 보고 갱신 또는 삽입을 정함
            반환값은 id를 담아 반환한다.
         */
        MemberNicknameHistory returnHistory;
        if(history.getId() == null) {
            return returnHistory = insert(history);
        }
        throw new UnsupportedOperationException("MemberNicknameHistory 는 갱신을 지원하지 않습니다.");
    }

    private MemberNicknameHistory insert(MemberNicknameHistory history) {
        // NamedParameterJdbcTemplate 내에 있는 keyHolder를 이용하여 구현도 가능하나, 다른방법으로 구현함
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(history);
        int id = simpleJdbcInsert.execute(params);

        return MemberNicknameHistory.builder()
                .id(Long.valueOf(id))
                .memberId(history.getMemberId())
                .nickname(history.getNickname())
                .createdAt(history.getCreatedAt())
                .build();
    }

    public Optional<MemberNicknameHistory> findById(Long id) {
        /*
         select * from member where id = ?
         */

        String sql = String.format("SELECT * FROM %s WHERE ID = :id", TABLE);
        var param = new MapSqlParameterSource()
                .addValue("id", id);

        RowMapper<MemberNicknameHistory> rowMapper = (ResultSet resultSet, int rowNum) ->
                MemberNicknameHistory.builder()
                    .id(resultSet.getLong("id"))
                    .memberId(resultSet.getLong("member_id"))
                    .nickname(resultSet.getString("nickname"))
                    .createdAt(resultSet.getObject("created_at", LocalDateTime.class))
                    .build();

        MemberNicknameHistory member = namedJdbcTemplate.queryForObject(sql, param, rowMapper);

        return Optional.ofNullable(member);
    }

    public List<MemberNicknameHistory> findAllByMemberId(Long memberId) {
        var sql = String.format("SELECT * FROM %s WHERE memberId = :memberId", TABLE);
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId);

//        return namedJdbcTemplate.query(sql, params, rowMapper)
        return null;
    }
}
