package com.oru.oruerakproject.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class MemberEntity {
    @Id
    @Column(name = "user_id")
    public String userId; // 닉네임(유저아이디)

    @Column(name = "phone_number")
    public String phoneNumber; // 전화번호

    @Column
    public Integer age; // 생년월일

    @Column
    public String sex; // 성별

    @Column
    public Double height; // 키

    @Column(nullable = false)
    public Double weight; // 체중
}
