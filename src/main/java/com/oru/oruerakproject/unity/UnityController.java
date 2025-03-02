package com.oru.oruerakproject.unity;

import com.oru.oruerakproject.unity.entitiy.UserCharactersEntitiy;
import com.oru.oruerakproject.unity.repository.UserCharactersRepository;
import com.oru.oruerakproject.unity.service.UserChractersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unity")
public class UnityController {
    private final UserCharactersRepository userCharactersRepository;

    private final UserChractersService userCharactersService;
    private final UserChractersService userChractersService;

    // 전체 보유 캐릭터 보내주는 api
    @GetMapping("/{userId}/get-character")
    public ResponseEntity<?> getCharacter(@PathVariable String userId) {
        List<UserCharactersEntitiy> userCharacters = userCharactersRepository.findByUserId(userId);

        if (userCharacters.isEmpty()) {
            return ResponseEntity.ok("보관함이 비었습니다.");
        }

        // false인 캐릭터 ID 들 가져와서 리스트에 저장
        List<Map<String, Object>> characterList = userCharacters.stream()
                .map(character -> {
                    Map<String, Object> characterMap = new HashMap<>();
                    characterMap.put("id", character.getCharacterId());
                    characterMap.put("open", character.isOpen()); // is_get 값 포함
                    return characterMap;
                })
                .collect(Collectors.toList());

        // JSON 배열 반환
        return ResponseEntity.ok(characterList);
    }

    // 캐릭터 F -> T로 해금해두는 api
    @GetMapping("/{userId}/open-character")
    public ResponseEntity<?> openCharacter(@PathVariable String userId) {
        List<UserCharactersEntitiy> userCharacters = userCharactersRepository.findByUserIdAndOpenFalse(userId);

        if (userCharacters.isEmpty()) {
            return ResponseEntity.ok("보관함이 비었습니다.");
        }

        // 변경된 캐릭터 ID 목록 저장
        List<Integer> unlockedCharacterIds = userCharacters.stream()
                .map(UserCharactersEntitiy::getCharacterId)
                .collect(Collectors.toList());

        // 모든 값을 is_get = true로 변경
        userCharacters.forEach(userCharacter -> userCharacter.setOpen(true));

        // 변경된 데이터를 한 번에 저장
        userCharactersRepository.saveAll(userCharacters);

        return ResponseEntity.ok("캐릭터들을 해금했습니다");
    }


    // 캐릭터 T - > F로 해금해두는 api
    @GetMapping("/{userId}/close-character")
    public ResponseEntity<?> closeCharacter(@PathVariable String userId) {
        List<UserCharactersEntitiy> userCharacters = userCharactersRepository.findByUserIdAndOpenTrue(userId);

        if (userCharacters.isEmpty()) {
            return ResponseEntity.ok("보관함이 비었습니다.");
        }

        // 변경된 캐릭터 ID 목록 저장
        List<Integer> unlockedCharacterIds = userCharacters.stream()
                .map(UserCharactersEntitiy::getCharacterId)
                .collect(Collectors.toList());

        // 모든 값을 is_get = true로 변경
        userCharacters.forEach(userCharacter -> userCharacter.setOpen(false));

        // 변경된 데이터를 한 번에 저장
        userCharactersRepository.saveAll(userCharacters);

        return ResponseEntity.ok("캐릭터들을 잠궜습니다");
    }

    @PutMapping("/{userId}/open-character/{characterId}")
    public ResponseEntity<Map<String, Object>> unlockCharacter(
            @PathVariable String userId,
            @PathVariable Integer characterId) {

        Map<String, Object> result = userCharactersService.getIsGetValue(userId, characterId);
        return ResponseEntity.ok(result);
    }
}
