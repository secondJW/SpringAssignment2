package com.sparta.schedule.controller;

import com.sparta.schedule.dto.SignupRequestDto;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;



    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
                //실패
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Location", "redirect:/api/user/signup").body("회원가입 실패");
        }

        // 반환 String
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "redirect:/api/user/signup").body(userService.signup(requestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse httpServletResponse, @RequestParam String username, @RequestParam String password) {
        userService.login(username, password);
        String token = jwtUtil.createToken(username);
        jwtUtil.addJwtToCookie(token, httpServletResponse);
        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }
}
