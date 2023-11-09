package com.YcTechAcademy.travelSchedules.member.controller;

import com.YcTechAcademy.travelSchedules.auth.TokenInfo;
import com.YcTechAcademy.travelSchedules.member.domain.Member;
import com.YcTechAcademy.travelSchedules.member.dto.LoginDto;
import com.YcTechAcademy.travelSchedules.member.dto.ResultDto;
import com.YcTechAcademy.travelSchedules.member.dto.SignupDto;
import com.YcTechAcademy.travelSchedules.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class UserController {

    private final MemberService memberService;
    @PostMapping("/auth/signUp")
    public ResponseEntity<ResultDto<Member>> signUp(@RequestBody SignupDto signupDto) {
        Member newMember = memberService.addUser(signupDto.userName(), signupDto.email(), signupDto.password());
        return ResponseEntity.ok()
                .body(new ResultDto<>(200, "", newMember));
    }

    @PostMapping(value = "/auth/login",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDto<TokenInfo>> login(@RequestBody LoginDto loginDto) {
        TokenInfo tokenInfo = memberService.login(loginDto.email(), loginDto.password());
        return ResponseEntity.ok()
                .header(SET_COOKIE,  generateCookie("accessToken", tokenInfo.accessToken()).toString())
                .header(SET_COOKIE,  generateCookie("refreshToken", tokenInfo.refreshToken()).toString())
                .body(new ResultDto<>(200, "", tokenInfo));
    }

    private ResponseCookie generateCookie(String from, String token) {
        return ResponseCookie.from(from, token)
                .httpOnly(true)
                .path("/")
                .build();
    }
}
