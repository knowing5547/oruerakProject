package com.oru.oruerakproject.records.dto;

import com.oru.oruerakproject.records.entity.RecordsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingsDto {
    private Double sceneryRating;
    private Double trailRating;
    private Double facilityRating;

    // Entity -> DTO
    public static RatingsDto toRating(RecordsEntity entity) {
        return RatingsDto.builder()
                .sceneryRating(entity.getSceneryRating())
                .trailRating(entity.getTrailRating())
                .facilityRating(entity.getFacilityRating())
                .build();
    }
    // DTO -> Entity
    public static RecordsEntity toRating(RatingsDto dto) {
        return RecordsEntity.builder()
                .sceneryRating(dto.getSceneryRating())
                .trailRating(dto.getTrailRating())
                .facilityRating(dto.getFacilityRating())
                .build();
    }
}