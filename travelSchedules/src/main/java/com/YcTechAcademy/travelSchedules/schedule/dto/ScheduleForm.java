package com.YcTechAcademy.travelSchedules.schedule.dto;

import com.YcTechAcademy.travelSchedules.schedule.domain.VisitStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ScheduleForm {
    private Long id;

    @NotBlank(message = "여행날짜는 필수입니다.")
    private String travelDate; //여행날짜

    @NotBlank(message = "여행지명은 필수입니다.")
    private String destination; //여행지명

    private String comment; //설명

    @NotNull(message = "방문상태는 필수입니다.")
    private VisitStatus visitStatus; //상태 [방문완료], [방문예정]

    private LocalDate updatedAt; //작성날짜

    private String locations; // 위치 정보 (lat1,lng1:lat2,lng2,...)
}
