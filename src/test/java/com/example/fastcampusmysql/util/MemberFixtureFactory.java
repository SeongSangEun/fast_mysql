package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {

    static public Member create() {
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(Member.class);

    }
    static public Member create(Long seed) {
        var param = new EasyRandomParameters();
        param.setSeed(seed);
        //SEED가 동일하다면 항상 같은 값을 보내주기 때문에 시드를 항상 다르게 보내줄 생각을 해야함
        // setSeed를 사용
        return new EasyRandom(param).nextObject(Member.class);
    }
}
