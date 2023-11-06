package com.YcTechAcademy.travelSchedules.member.domain;

import com.YcTechAcademy.travelSchedules.schedule.domain.Schedule;
import com.YcTechAcademy.travelSchedules.member.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "memeber_id")
    private UUID id;

    @Column(name="username")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Schedule> schedules = new ArrayList<>();

    @JsonIgnore
    @Column(name="password")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
