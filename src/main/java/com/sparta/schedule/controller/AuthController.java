package com.sparta.schedule.controller;

import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtUtil jwtUtil;
    private final ScheduleService scheduleService;


    @GetMapping("/create-jwt")
    public String createJwt(HttpServletResponse res, @RequestParam String manager) {
        // Jwt 생성
        String token = jwtUtil.createToken(manager);

        // Jwt 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);

        return "createJwt : " + token;
    }


}
