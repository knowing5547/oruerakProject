package com.oru.oruerakproject.manager.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_logs")
public class ManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;         // 사용자 ID


    private String activity;     // 사용자가 클릭한 기능 (예: "HOME", "SEARCH", "PROFILE" 등)

    @Column(name = "access_time")
    private LocalDateTime accessTime;  // 서버 기준 접속 시각
}
