package com.YcTechAcademy.travelSchedules.service;

import com.YcTechAcademy.travelSchedules.schedule.domain.Schedule;
import com.YcTechAcademy.travelSchedules.schedule.dto.ScheduleForm;
import com.YcTechAcademy.travelSchedules.schedule.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Test
//    @Rollback(value = false)
    void findAllSchedules() {
        Schedule schedule1 = new Schedule();
        schedule1.setDestination("서울");
        Schedule schedule2 = new Schedule();
        schedule2.setDestination("경기");

        scheduleService.join(schedule1);
        scheduleService.join(schedule2);

        assertThat(scheduleService.findAllSchedules().size()).isEqualTo(2);
    }

    @Test
    void join() {
        Schedule schedule1 = new Schedule();
        schedule1.setDestination("서울");

        Long savedId = scheduleService.join(schedule1);

        assertThat(savedId).isEqualTo(schedule1.getId());

    }

    @Test
    void findSchedule() {
        Schedule schedule1 = new Schedule();
        schedule1.setDestination("서울");

        Long savedId = scheduleService.join(schedule1);
        Schedule findSchedule = scheduleService.findSchedule(savedId);

        assertThat(findSchedule).isEqualTo(schedule1);
    }

    @Test
    void updateSchedule() {
        Schedule schedule1 = new Schedule();
        schedule1.setDestination("서울");

        Long savedId = scheduleService.join(schedule1);

        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setDestination("부산");

        scheduleService.updateSchedule(savedId, scheduleForm);

        assertThat(scheduleService.findSchedule(savedId).getDestination()).isEqualTo("부산");
    }

    @Test
    void deleteSchedule() {
        Schedule schedule1 = new Schedule();
        schedule1.setDestination("서울");

        Long savedId = scheduleService.join(schedule1);

        scheduleService.deleteSchedule(savedId);

        assertThat(scheduleService.findAllSchedules().size()).isEqualTo(0);
    }
}