package com.YcTechAcademy.travelSchedules.service;

import com.YcTechAcademy.travelSchedules.member.domain.Member;
import com.YcTechAcademy.travelSchedules.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
//    @Rollback(value = false)
    void findMembers() {
        Member member1 = new Member();
        member1.setUsername("member1");
        Member member2 = new Member();
        member2.setUsername("member2");

        memberService.join(member1);
        memberService.join(member2);

        List<Member> members = memberService.findMembers();


        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void join() {
        Member member1 = new Member();
        member1.setUsername("member1");

        UUID savedId = memberService.join(member1);


        assertThat(savedId).isEqualTo(member1.getId());
    }
}