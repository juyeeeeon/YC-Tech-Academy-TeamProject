package com.YcTechAcademy.travelSchedules.member.dto;

public record ResultDto<T>(
        int code,
        String message,
        T data
) {

}