package com.YcTechAcademy.travelSchedules.service;

import com.YcTechAcademy.travelSchedules.domain.Schedule;
import com.YcTechAcademy.travelSchedules.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> findAllSchedules() {
        return null;
    }

    public void join(Schedule schedule) {

    }

    public Schedule findSchedule(Long id) {
        return null;
    }

    public void updateSchedule(Long id, LocalDate travelDate, String destination, String formDestination) {

    }

    public void deleteSchedule(Long id) {

    }
}
