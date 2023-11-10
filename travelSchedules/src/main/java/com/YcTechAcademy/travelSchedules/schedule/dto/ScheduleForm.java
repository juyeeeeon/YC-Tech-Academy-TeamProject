package com.YcTechAcademy.travelSchedules.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleForm {
    private Long id;

    private String travelDate; //여행날짜

    private String destination; //여행지명

    private String comment; //설명

    private String visitStatus; //상태 [NOT_VISITED], [VISITED]

    private String writer; //작성자

    private String writeDate; //작성날짜
}
