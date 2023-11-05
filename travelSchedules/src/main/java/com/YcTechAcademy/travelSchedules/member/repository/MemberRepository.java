package com.YcTechAcademy.travelSchedules.member.repository;

import com.YcTechAcademy.travelSchedules.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmail(String email);
}
