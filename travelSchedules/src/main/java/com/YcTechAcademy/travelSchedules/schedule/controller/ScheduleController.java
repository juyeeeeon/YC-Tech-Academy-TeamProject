package com.YcTechAcademy.travelSchedules.schedule.controller;

import com.YcTechAcademy.travelSchedules.schedule.domain.Schedule;
import com.YcTechAcademy.travelSchedules.schedule.dto.ScheduleForm;
import com.YcTechAcademy.travelSchedules.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules")
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
     * 특정 여행일정 조회
     */
    @GetMapping("/{id}")
    public String findSchedule(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.findSchedule(id);
        model.addAttribute("scheduleForm", schedule);

        return "schedules/findScheduleForm";
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
        schedule.setVisitStatus(form.getVisitStatus());
        schedule.setWriter(form.getWriter());
        schedule.setWriteDate(form.getWriteDate());

        scheduleService.join(schedule);

        return "redirect:/schedules";
    }

    /**
     * 특정 여행일정 수정화면 조회
     */
    @GetMapping("/{id}/edit")
    public String updateScheduleForm(@PathVariable Long id, Model model) {

        Schedule schedule = scheduleService.findSchedule(id);

        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setTravelDate(schedule.getTravelDate());
        scheduleForm.setDestination(schedule.getDestination());
        scheduleForm.setComment(schedule.getComment());
        scheduleForm.setVisitStatus(schedule.getVisitStatus());
        scheduleForm.setWriter(schedule.getWriter());
        scheduleForm.setWriteDate(schedule.getWriteDate());

        model.addAttribute("scheduleForm", scheduleForm);

        return "schedules/updateScheduleForm";
    }

    /**
     * 특정 여행일정 수정
     */
    @PostMapping("/{id}/edit")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute("form") ScheduleForm form) {

        scheduleService.updateSchedule(id, form);

        return "redirect:/schedules";
    }

    /**
     * 특정 여행일정 삭제
     */
    @GetMapping("/{id}/delete")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return "redirect:/schedules";
    }
}
