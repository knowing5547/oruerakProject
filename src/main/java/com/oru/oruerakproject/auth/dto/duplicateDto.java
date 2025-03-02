package com.oru.oruerakproject.auth.dto;

import com.oru.oruerakproject.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class duplicateDto {
    private String userId;
    private String phoneNumber;

    public static duplicateDto toduplicateDto(MemberEntity member) {
        return duplicateDto.builder()
                .userId(member.getUserId())
                .phoneNumber(member.getPhoneNumber())
                .build();

    }
}
