package com.YcTechAcademy.travelSchedules.member.controller;

import com.YcTechAcademy.travelSchedules.member.domain.Address;
import com.YcTechAcademy.travelSchedules.member.domain.Member;
import com.YcTechAcademy.travelSchedules.member.dto.LoginDto;
import com.YcTechAcademy.travelSchedules.member.dto.MemberForm;
import com.YcTechAcademy.travelSchedules.member.dto.ResultDto;
import com.YcTechAcademy.travelSchedules.member.dto.SignupDto;
import com.YcTechAcademy.travelSchedules.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /*
     * 회원목록 조회
     */
    @GetMapping
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    /**
     * 회원 저장화면 조회
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     * 회원 저장(생성)
     */
    @PostMapping("/new")
    public String create(MemberForm form) {

        Member member = new Member();

        member.setUsername(form.getUsername());
        member.setEmail(form.getEmail());
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/schedules";
    }
}
