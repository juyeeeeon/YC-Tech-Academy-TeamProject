package com.YcTechAcademy.travelSchedules.member.service;

import com.YcTechAcademy.travelSchedules.member.domain.Member;
import com.YcTechAcademy.travelSchedules.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
    }

    ////////////////////////////
    @Transactional
    public UUID join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }


}
