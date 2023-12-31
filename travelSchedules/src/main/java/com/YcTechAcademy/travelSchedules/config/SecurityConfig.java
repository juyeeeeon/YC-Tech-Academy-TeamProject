package com.YcTechAcademy.travelSchedules.config;

import com.YcTechAcademy.travelSchedules.auth.JwtAuthFilter;
import com.YcTechAcademy.travelSchedules.auth.OAuthSuccessHandler;
import com.YcTechAcademy.travelSchedules.member.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()// csrf 방지

                //Form Login
                .formLogin().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()

                .antMatchers("/", "/login", "/signup", "/members/auth/**", "/h2-console/**")
                .permitAll() //antMatchers 설정한 리소스의 접근을 인증절차 없이 허용

                .anyRequest().authenticated()

                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                //Oauth2 Login
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .successHandler(oAuthSuccessHandler)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        //logout
        http.logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .addLogoutHandler((request, response, auth) -> {
            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();
                Cookie cookieToDelete = new Cookie(cookieName, null);
                cookieToDelete.setMaxAge(0);
                response.addCookie(cookieToDelete);
            }
        })
                );
        return http.build();




    }

}