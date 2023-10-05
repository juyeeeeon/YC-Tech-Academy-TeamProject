package com.YcTechAcademy.travelSchedules.repository;

import com.YcTechAcademy.travelSchedules.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface memberRepository extends JpaRepository<Member, Long> {
}
