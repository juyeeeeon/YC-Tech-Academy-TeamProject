package com.YcTechAcademy.travelSchedules.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleForm {
    private Long id;

    @NotBlank(message = "여행날짜는 필수입니다.")
//    @Future(message = "여행날짜는 과거일 수 없습니다.")
    private String travelDate; //여행날짜

    @NotBlank(message = "여행지명은 필수입니다.")
    private String destination; //여행지명

    private String comment; //설명

    private String visitStatus; //상태 [NOT_VISITED], [VISITED]

    private String writeDate; //작성날짜
}
