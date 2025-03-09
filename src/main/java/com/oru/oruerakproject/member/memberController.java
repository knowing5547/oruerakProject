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

    // userId 넣으면 유저정보 다 주는 api
    @PostMapping("/find-user-info/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable String userId) {
        Optional<MemberDto> dto = memberservice.infoUserId(userId);
        return ResponseEntity.ok(dto);
    }

    // 각 유저의 총 등산갯수 주는 api
    @GetMapping("/count/{userId}")
    public ResponseEntity<Integer> getUserHikingCount(@PathVariable String userId) {
        int count = memberservice.getUserHikingCount(userId);
        return ResponseEntity.ok(count);
    }

}
