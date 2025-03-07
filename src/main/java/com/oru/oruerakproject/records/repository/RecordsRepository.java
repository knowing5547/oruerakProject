package com.oru.oruerakproject.records.repository;

import com.oru.oruerakproject.records.dto.RecordsDto;
import com.oru.oruerakproject.records.entity.RecordsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordsRepository extends JpaRepository<RecordsEntity, Long> {

    // 최근 등산기록 가져오기
    RecordsEntity findTopByUserIdOrderByHikeDateDesc(String userId);

    // 모든 등산기록 가져오기
    Page<RecordsEntity> findByUserIdOrderByHikeDateDesc(String userId, Pageable pageable);

    // 특정 등산기록 가져오기
    Optional<RecordsEntity> findByIdAndUserId(Long id, String userId);
}
