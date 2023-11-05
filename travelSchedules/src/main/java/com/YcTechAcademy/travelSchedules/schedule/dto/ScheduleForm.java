package com.YcTechAcademy.travelSchedules.schedule.dto;

import com.YcTechAcademy.travelSchedules.schedule.domain.VisitStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleForm {

    private LocalDate travelDate; //여행날짜

    private String destination; //여행지명

    private String comment; //설명

    private VisitStatus visitStatus; //상태 [NOT_VISITED], [VISITED]

    private String writer; //작성자

    private LocalDateTime writeDate; //작성날짜
}
