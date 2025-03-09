package com.oru.oruerakproject.member.repository;

import com.oru.oruerakproject.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findByPhoneNumber(String phoneNumber);

    Optional<MemberEntity> findByUserId(String UserId);

    String phoneNumber(String phoneNumber);
}
