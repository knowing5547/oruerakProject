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
public class MemberDto {
    private String userId;
    private String phoneNumber;
    private Integer age;
    private String sex;
    private Double height;
    private Double weight;

    public static MemberEntity toMemberEntity(MemberDto dto) {
        return MemberEntity.builder()
                .userId(dto.getUserId())
                .phoneNumber(dto.getPhoneNumber())
                .age(dto.getAge())
                .sex(dto.getSex())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .build();
    }

    public static MemberDto toMemberDto(MemberEntity entity) {
        return MemberDto.builder()
                .userId(entity.getUserId())
                .phoneNumber(entity.getPhoneNumber())
                .age(entity.getAge())
                .sex(entity.getSex())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .build();
    }

}
