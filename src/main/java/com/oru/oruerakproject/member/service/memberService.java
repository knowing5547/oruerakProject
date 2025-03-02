package com.oru.oruerakproject.member.service;




import com.oru.oruerakproject.common.repository.CommonRepository;
import com.oru.oruerakproject.member.dto.MemberDto;
import com.oru.oruerakproject.member.dto.UserIdDto;
import com.oru.oruerakproject.member.entity.MemberEntity;
import com.oru.oruerakproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class memberService {
    private final CommonRepository commonRepository;
    private final MemberRepository memberRepository;

    public void signUp(MemberDto memberDto) {
            // DTO → Entity 변환
            MemberEntity memberconvEntity = MemberDto.toMemberEntity(memberDto);

            // Entity 저장
            commonRepository.save(memberconvEntity);
    }

    public Optional<UserIdDto> getUserIdByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber)
                .map(UserIdDto::toUserId);
    }
}
