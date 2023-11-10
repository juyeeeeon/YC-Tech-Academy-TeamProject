package com.YcTechAcademy.travelSchedules.schedule.repository;

import com.YcTechAcademy.travelSchedules.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
