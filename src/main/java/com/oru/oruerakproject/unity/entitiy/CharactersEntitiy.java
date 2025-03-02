package com.oru.oruerakproject.unity.entitiy;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.*;

// 캐릭터 = 산 연관짓는 테이블

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
public class CharactersEntitiy {
    @Id
    @Column(name = "character_id")
    private int characterId; // 캐릭터 ID

    @Column
    private String name; // 캐릭터 이름

    @Column(name = "mountain_name")
    private String mountainName; // 캐릭터와 연결된 산

//    @Column
//    private Enum category;
//
//    @Column(columnDefinition = "TEXT")
//    private String description;
//
//    @Column(name = "conservation_status")
//    private int conservationStatus;
//
//    @Column
//    private String image;

}
