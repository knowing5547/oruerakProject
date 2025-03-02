package com.oru.oruerakproject.unity.entitiy;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_characters")
public class UserCharactersEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_character_id")
    private int userCharacterId; // 고유 ID

    @Column(name = "user_id")
    private String userId; // 유저 닉네임(ID)

    @Column(name = "character_id")
    private int characterId; // 연결된 캐릭터 ID

    @Column(name = "is_get")
    private boolean open = false; // 캐릭터 해금 여부 (기본값 : false)
}
