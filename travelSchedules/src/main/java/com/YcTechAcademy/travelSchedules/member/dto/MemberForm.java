package com.YcTechAcademy.travelSchedules.member.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberForm {
    private String username;

    private String email;

    private String city;
    private String street;
    private String zipcode;
}
