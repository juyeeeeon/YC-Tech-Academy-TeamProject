package com.YcTechAcademy.travelSchedules.controller;

import com.YcTechAcademy.travelSchedules.domain.*;
import com.YcTechAcademy.travelSchedules.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 전체 여행일정표 조회
     */
    @GetMapping
    public String findAllSchedules(Model model) {
        List<Schedule> schedules = scheduleService.findAllSchedules();
        model.addAttribute("schedules", schedules);
        return "schedules/scheduleList";
    }

    /**
     * 여행 일정 저장화면 조회
     */
    @GetMapping("/new")
    public String createScheduleForm(Model model) {
        model.addAttribute("scheduleForm", new ScheduleForm());
        return "schedules/createScheduleForm";
    }

    /**
     * 여행 일정 저장(생성)
     */
    @PostMapping("/new")
    public String createSchedule(ScheduleForm form) {

        Schedule schedule = new Schedule();

        schedule.setTravelDate(form.getTravelDate());
        schedule.setDestination(form.getDestination());
        schedule.setComment(form.getComment());

        scheduleService.join(schedule);

        return "redirect:/schedules";
    }

    /**
     * 특정 여행일정 조회
     */
    @GetMapping("/{id}")
    public String findSchedule(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.findSchedule(id);

        return "schedules/findScheduleForm";
    }

    /**
     * 특정 여행일정 수정
     */
    @PostMapping("/{id}/edit")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute("form") ScheduleForm form) {

        scheduleService.updateSchedule(id, form.getTravelDate(), form.getDestination(), form.getDestination());

        return "redirect:/schedules";
    }

    /**
     * 특정 여행일정 삭제
     */
    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return "redirect:/schedules";
    }
}
