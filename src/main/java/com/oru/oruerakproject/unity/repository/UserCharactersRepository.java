package com.oru.oruerakproject.unity.repository;

import com.oru.oruerakproject.unity.entitiy.UserCharactersEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 사용자가 획득한 캐릭터 관련 데이터 조회를 위한 레포지토리
@Repository
public interface UserCharactersRepository extends JpaRepository<UserCharactersEntitiy, Long> {
    // 아직 해금되지 않은 캐릭터 조회
    List<UserCharactersEntitiy> findByUserIdAndOpenFalse(String userId);
    // 아직 해금되지 않은 캐릭터 조회
    List<UserCharactersEntitiy> findByUserIdAndOpenTrue(String userId);

    // 가장 먼저 해금될 캐릭터 조회
    Optional<UserCharactersEntitiy> findTopByUserIdAndOpenFalse(String userId);
    // 특정 유저가 특정 캐릭터를 보유하고 있는지 확인
    Optional<UserCharactersEntitiy> findByUserIdAndCharacterId(String userId, int characterId);

    List<UserCharactersEntitiy> findByUserId(String userId);
}
