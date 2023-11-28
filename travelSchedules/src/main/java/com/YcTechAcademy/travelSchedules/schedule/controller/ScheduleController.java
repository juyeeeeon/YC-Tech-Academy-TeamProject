package com.YcTechAcademy.travelSchedules.schedule.controller;

import com.YcTechAcademy.travelSchedules.schedule.domain.Schedule;
import com.YcTechAcademy.travelSchedules.schedule.dto.ScheduleForm;
import com.YcTechAcademy.travelSchedules.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 전체 여행일정표 조회
     */
    @GetMapping
    public String findAllSchedules(Model model,
                                   @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                   String searchKeyword) {

        /*검색기능-3*/
        Page<Schedule> schedules = null;

        /*searchKeyword = 검색하는 단어*/
        if (searchKeyword == null) {
            schedules = scheduleService.findAllSchedules(pageable); //기존의 리스트보여줌
        } else {
            schedules = scheduleService.scheduleSearchList(searchKeyword, pageable); //검색리스트반환
        }

        int nowPage = schedules.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재페이지를 가지고올수있다 * 0부터시작하니까 +1
        int startPage = Math.max(nowPage - 4, 1); //매개변수로 들어온 두 값을 비교해서 큰값을 반환
        int endPage;
        if (schedules.getTotalPages() == 0) {
            endPage = Math.min(nowPage + 5, schedules.getTotalPages()+1);
        }else endPage = Math.min(nowPage + 5, schedules.getTotalPages());

        //BoardService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
        model.addAttribute("schedules", schedules);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "schedules/scheduleList";
    }

    /**
     * 특정 여행일정 조회
     */
    @GetMapping("/{id}")
    public String findSchedule(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.findSchedule(id);
        model.addAttribute("schedule", schedule);

        return "schedules/schedule";
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
    public String createSchedule(@Valid ScheduleForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "schedules/createScheduleForm";
        }

        Schedule schedule = new Schedule();

        schedule.setTravelDate(form.getTravelDate());
        schedule.setDestination(form.getDestination());
        schedule.setComment(form.getComment());
        schedule.setVisitStatus(form.getVisitStatus());
        schedule.setLocations(form.getLocations());

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
        scheduleForm.setUpdatedAt(schedule.getUpdatedAt());
        scheduleForm.setLocations(schedule.getLocations());

        model.addAttribute("scheduleForm", scheduleForm);

        return "schedules/updateScheduleForm";
    }

    /**
     * 특정 여행일정 수정
     */
    @PostMapping("/{id}/edit")
    public String updateSchedule(@PathVariable Long id, @Valid @ModelAttribute("scheduleForm") ScheduleForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "schedules/updateScheduleForm";
        }

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
