package com.example.fastcampusmysql.domain.member;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public void testChangeNickname() {


        // objectMother patten
        // EasyRandom 사용하여 여러 객체 생성방법
//        LongStream.range(0, 10)
//                .mapToObj(MemberFixtureFactory::create)
//                .forEach(member -> {
//                    System.out.println("member.getNickName() = " + member.getNickName());
//                });

        var member = MemberFixtureFactory.create();
        var newNickname = "pnu";

        member.changeNickname(newNickname);

//        Assertions.assertThat(member.getNickName()).isEqualTo(newNickname);
        Assertions.assertEquals(newNickname, member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    public void testNicknameMaxLength() {

        var member = MemberFixtureFactory.create();
        var overMaxLengthNickname = "sangeunseong";

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> member.changeNickname(overMaxLengthNickname));
    }
}
