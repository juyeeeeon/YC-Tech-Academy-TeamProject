package com.YcTechAcademy.travelSchedules.member.repository;

import com.YcTechAcademy.travelSchedules.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmail(String email);
}
