package com.oru.oruerakproject.member.dto;

import com.oru.oruerakproject.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserIdDto {
    private String userId;

    // output 닉네임 Entity -> Dto
    public static UserIdDto toUserId(MemberEntity entity) {
        return UserIdDto.builder()
                .userId(entity.getUserId())
                .build();
    }
}
