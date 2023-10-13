package com.YcTechAcademy.travelSchedules.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleForm {

    private LocalDateTime travelDate; //여행날짜

    private String destination; //여행지명

    private String comment; //설명
}
