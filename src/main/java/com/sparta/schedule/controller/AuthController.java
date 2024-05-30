package com.sparta.schedule.controller;

import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.service.ScheduleService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
