package com.oru.oruerakproject.records.dto;

import com.oru.oruerakproject.records.entity.RecordsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordsDto {
    private long id;
    private String userId;
    private LocalDateTime hikeDate;
    private String mountainName;
    private Double totalTime;
    private Double restTime;
    private Double distance;
    private Double speed;
    private double accumulatedAltitude;
    private double currentAltitude;
    private double caloriesBurned;
    private String geoJsonCoordinates;
    private Long mountainId;
    private Boolean isCompleted;

    // DTO -> Entity 변환 메서드
    public static RecordsEntity toEntity(RecordsDto dto) {
        return RecordsEntity.builder()
                .userId(dto.getUserId())
                .hikeDate(dto.getHikeDate())
                .mountainName(dto.getMountainName())
                .totalTime(dto.getTotalTime())
                .restTime(dto.getRestTime())
                .distance(dto.getDistance())
                .speed(dto.getSpeed())
                .accumulatedAltitude(dto.getAccumulatedAltitude())
                .caloriesBurned(dto.getCaloriesBurned())
                .geoJsonCoordinates(dto.getGeoJsonCoordinates())
                .mountainId(dto.getMountainId())
                .isCompleted(dto.getIsCompleted())
                .build();
    }

    // Entity → DTO 변환 메서드
    public static RecordsDto fromEntity(RecordsEntity entity) {
        return RecordsDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .hikeDate(entity.getHikeDate())
                .mountainName(entity.getMountainName())
                .totalTime(entity.getTotalTime())
                .restTime(entity.getRestTime())
                .distance(entity.getDistance())
                .speed(entity.getSpeed())
                .accumulatedAltitude(entity.getAccumulatedAltitude())
                .caloriesBurned(entity.getCaloriesBurned())
                .geoJsonCoordinates(entity.getGeoJsonCoordinates()) // GeoJSON 추가
                .mountainId(entity.getMountainId())
                .isCompleted(entity.getIsCompleted())
                .build();
    }


}
