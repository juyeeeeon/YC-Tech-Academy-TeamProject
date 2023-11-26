package com.YcTechAcademy.travelSchedules.schedule.repository;

import com.YcTechAcademy.travelSchedules.schedule.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    /**
     * 검색
     */
    Page<Schedule> findByDestinationContaining(String searchKeyword, Pageable pageable);

    Page<Schedule> findAllByOrderByTravelDateAsc(Pageable pageable);
}
