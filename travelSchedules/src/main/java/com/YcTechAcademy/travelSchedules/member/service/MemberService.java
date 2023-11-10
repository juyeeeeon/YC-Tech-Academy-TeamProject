package com.YcTechAcademy.travelSchedules.member.service;

import com.YcTechAcademy.travelSchedules.auth.JwtTokenProvider;
import com.YcTechAcademy.travelSchedules.auth.TokenInfo;
import com.YcTechAcademy.travelSchedules.member.domain.Member;
import com.YcTechAcademy.travelSchedules.member.domain.RefreshToken;
import com.YcTechAcademy.travelSchedules.member.repository.MemberRepository;
import com.YcTechAcademy.travelSchedules.member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
    }

    ////////////////////////////
    @Transactional
    public UUID join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Member addUser(String userName, String email, String password) {
        Member member = new Member(userName, email, passwordEncoder.encode(password),
                Set.of("USER"));
        return memberRepository.save(member);
    }

    @Transactional
    public TokenInfo login(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder
                .getObject().authenticate(authenticationToken);

        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isEmpty()) {
            throw new AccessDeniedException("not found user");
        }

        // 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication, email);

        // refresh token 없으면 생성 or update
        refreshTokenRepository.findByMember_Email(member.get().getEmail())
                .ifPresentOrElse(refreshToken -> {
                    refreshToken.setRefreshToken(tokenInfo.refreshToken());
                    refreshTokenRepository.save(refreshToken);
                }, () -> {
                    refreshTokenRepository.save(new RefreshToken(tokenInfo.refreshToken(), member.get()));
                });

        return tokenInfo;
    }

}
