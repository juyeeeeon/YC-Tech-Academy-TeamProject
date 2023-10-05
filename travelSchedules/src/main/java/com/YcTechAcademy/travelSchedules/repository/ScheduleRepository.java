package com.YcTechAcademy.travelSchedules.repository;

import com.YcTechAcademy.travelSchedules.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
