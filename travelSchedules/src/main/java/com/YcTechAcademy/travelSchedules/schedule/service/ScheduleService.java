package com.YcTechAcademy.travelSchedules.schedule.service;

import com.YcTechAcademy.travelSchedules.schedule.domain.Schedule;
import com.YcTechAcademy.travelSchedules.schedule.dto.ScheduleForm;
import com.YcTechAcademy.travelSchedules.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Page<Schedule> findAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Transactional
    public Long join(Schedule schedule) {
        scheduleRepository.save(schedule);
        return schedule.getId();
    }

    public Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("여행 계획이 존재하지 않습니다."));
    }

    @Transactional
    public void updateSchedule(Long id, ScheduleForm scheduleForm) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("여행 계획이 존재하지 않습니다."));

        schedule.update(scheduleForm.getTravelDate(), scheduleForm.getDestination(), scheduleForm.getComment(),
                scheduleForm.getVisitStatus(), scheduleForm.getWriteDate());

    }

    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("여행 계획이 존재하지 않습니다."));

        scheduleRepository.delete(schedule);
    }

    /*검색기능-2*/
    //검색
    public Page<Schedule> scheduleSearchList(String searchKeyword, Pageable pageable){
        return scheduleRepository.findByDestinationContaining(searchKeyword, pageable);
    }
}
