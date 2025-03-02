package com.oru.oruerakproject.common.repository;

import com.oru.oruerakproject.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonRepository extends JpaRepository<MemberEntity, String> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUserId(String userId); // 닉네임 중복 확인

    Optional<MemberEntity> findByPhoneNumber(String phoneNumber);
}

