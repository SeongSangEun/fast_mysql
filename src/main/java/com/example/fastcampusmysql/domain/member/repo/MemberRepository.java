package com.example.fastcampusmysql.domain.member.repo;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public Member save(Member member) {
        /*
            member id를 보고 갱신 또는 삽입을 정함
            반환값은 id를 담아 반환한다.
         */
        Member returnMember;
        if(member.getId() == null) {
            returnMember = insert(member);
        } else {
            returnMember = update(member);
        }

        return returnMember;
    }

    private Member insert(Member member) {
        // NamedParameterJdbcTemplate 내에 있는 keyHolder를 이용하여 구현도 가능하나, 다른방법으로 구현함
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        int id = simpleJdbcInsert.execute(params);

        return Member.builder()
                .id(Long.valueOf(id))
                .email(member.getEmail())
                .birthDay(member.getBirthDay())
                .nickName(member.getNickName())
                .createdAt(member.getCreatedAt())
                .build();
    }

    //todo 업데이트는 숙제
    private Member update(Member member) {
        return member;
    }
}
