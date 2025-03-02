package com.oru.oruerakproject.auth;

import com.oru.oruerakproject.auth.service.authService;
import com.oru.oruerakproject.auth.service.duplicateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class authController {
    private final authService authservice;

    // 인증번호 전송 API
    @PostMapping("/auth/request-auth-code")
    public ResponseEntity<?> sendVerificationCode(@RequestParam String phoneNumber) {
        authservice.sendVerificationCode(phoneNumber);
        return ResponseEntity.ok(Map.of("message", "인증번호가 전송되었습니다."));
    }

    // 인증번호 확인 API
    @PostMapping("/auth/check-auth-code")
    public ResponseEntity<?> verifyCode(@RequestParam String phoneNumber, @RequestParam String verificationCode) {
        boolean isValid = authservice.verifyCode(phoneNumber, verificationCode);

        if (isValid) {
            return ResponseEntity.ok(Map.of("message", "인증 성공"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "인증 실패"));
        }
    }

    private final duplicateService duplicateService;

    // 🔹 전화번호 중복 체크 API
    @GetMapping("auth/duplicate-phone")
    public ResponseEntity<String> checkPhone(@RequestParam String phoneNumber) {
        boolean isDuplicate = duplicateService.isPhoneDuplicate(phoneNumber);
        if (!isDuplicate) {
            return ResponseEntity.ok("전화번호 사용 가능");
        }
        return ResponseEntity.ok("전화번호 사용 불가능(중복)");
    }

    // 🔹 닉네임 중복 체크 API
    @GetMapping("auth/duplicate-nickname")
    public ResponseEntity<String> checkNickname(@RequestParam String userId) {
        boolean isDuplicate = duplicateService.isNicknameDuplicate(userId);
        if (!isDuplicate) {
            return ResponseEntity.ok("닉네임 사용 가능");
        }
        return ResponseEntity.ok("닉네임 사용 불가능(중복)");
    }
}
