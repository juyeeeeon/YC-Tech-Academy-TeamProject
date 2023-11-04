package com.YcTechAcademy.travelSchedules.service;

import com.YcTechAcademy.travelSchedules.domain.Member;
import com.YcTechAcademy.travelSchedules.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findMembers() {
        return null;
    }

    public void join(Member member) {

    }
}
