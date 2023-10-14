package com.YcTechAcademy.travelSchedules.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate travelDate; //여행날짜

    private String destination; //여행지명

    private String comment; //설명

    @Enumerated(EnumType.STRING)
    private VisitStatus visitStatus; //상태 [NOT_VISITED], [VISITED]

    private String note; //비고

    private String writer; //작성자

    private LocalDateTime writeDate; //작성날짜

    //연관관계메서드
    public void setMember(Member member) {
        this.member = member;
        member.getSchedules().add(this);
    }
}
