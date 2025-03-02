package com.oru.oruerakproject.member;



import com.oru.oruerakproject.member.dto.MemberDto;
import com.oru.oruerakproject.member.dto.UserIdDto;
import com.oru.oruerakproject.member.service.memberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class memberController {
    private final memberService memberservice;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody MemberDto memberDto) {
        memberservice.signUp(memberDto);
        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }

    @PostMapping("/find-user-id/{phoneNumber}")
    public ResponseEntity<?> getUserId(@PathVariable String phoneNumber) {
        Optional<UserIdDto> userIdDto = memberservice.getUserIdByPhoneNumber(phoneNumber);

        return userIdDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
}
