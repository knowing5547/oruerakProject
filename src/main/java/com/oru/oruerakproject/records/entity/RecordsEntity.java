package com.oru.oruerakproject.records.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hiking_records")
public class RecordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId; // 유저 닉네임(아이디)

    @Column(name = "hike_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime hikeDate; // 등산 시간

    @Column
    private String mountainName; // 산 이름

    @Column
    private double totalTime; // 총 시간 (단위: 분 또는 시간)

    @Column
    private double restTime; // 휴식 시간 (단위: 분 또는 시간)

    @Column
    private double distance; // 이동 거리 (단위: km)

    @Column
    private double speed; // 산행 속도 (단위: km/h)

    @Column
    private double accumulatedAltitude; // 누적 고도 상승량 (단위: m)

    @Column
    private double caloriesBurned; // 소모 칼로리 (단위: kcal)

    @Column(columnDefinition = "TEXT") // JSON 형식 저장
    private String geoJsonCoordinates; // 이동 좌표 (GeoJSON 형식)

    @Column(name = "mountain_id")
    private Long mountainId;

    @Column(name = "is_completed")
    private Boolean isCompleted;

}
