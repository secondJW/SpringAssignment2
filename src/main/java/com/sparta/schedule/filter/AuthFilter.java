package com.sparta.schedule.filter;

import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.repository.ScheduleRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;


@Slf4j(topic = "AuthFilter")
public class AuthFilter implements Filter {
    private final ScheduleRepository scheduleRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(ScheduleRepository userRepository, JwtUtil jwtUtil) {
        this.scheduleRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
         if (StringUtils.hasText(url) &&
                (url.startsWith("/api/create-jwt"))
        ) {
            // 인증절차
             String manager = httpServletRequest.getParameter("manager");
             if(scheduleRepository.findByManager(manager).isEmpty()){
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"유효한 토큰이 아님");
             }
             chain.doFilter(request, response);
            // log.info("인증처리 하지 않는 url : "+url);
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행

        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                Schedule schedule = scheduleRepository.findByManager(info.getSubject()).orElseThrow(() ->
                        new NullPointerException("Not Found Manager")
                );
                request.setAttribute("manager", info.getSubject());
                chain.doFilter(request, response);

            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}
