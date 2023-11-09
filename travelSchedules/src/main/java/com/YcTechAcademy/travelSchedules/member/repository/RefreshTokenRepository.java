package com.YcTechAcademy.travelSchedules.member.repository;

import com.YcTechAcademy.travelSchedules.member.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByMember_Email(String email);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}