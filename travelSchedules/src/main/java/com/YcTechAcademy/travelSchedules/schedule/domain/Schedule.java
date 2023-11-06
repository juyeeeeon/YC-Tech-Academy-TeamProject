package com.YcTechAcademy.travelSchedules.schedule.domain;

import com.YcTechAcademy.travelSchedules.member.domain.Member;
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

    private String writer; //작성자

    private LocalDateTime writeDate; //작성날짜

    public void update(LocalDate travelDate, String destination, String comment, VisitStatus visitStatus, String writer, LocalDateTime writeDate) {
        this.travelDate = travelDate;
        this.destination = destination;
        this.comment = comment;
        this.visitStatus = visitStatus;
        this.writer = writer;
        this.writeDate = writeDate;
    }

    //연관관계메서드
    public void setMember(Member member) {
        this.member = member;
        member.getSchedules().add(this);
    }
}