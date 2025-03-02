package com.oru.oruerakproject.unity.service;

import com.oru.oruerakproject.unity.entitiy.UserCharactersEntitiy;
import com.oru.oruerakproject.unity.repository.UserCharactersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserChractersService {
    @Autowired
    private UserCharactersRepository userCharactersRepository; // Repository 주입

    @Transactional
    public Map<String, Object> getIsGetValue(String userId, Integer characterId) {
        Optional<UserCharactersEntitiy> userCharacterOpt = userCharactersRepository.findByUserIdAndCharacterId(userId, characterId);

        Map<String, Object> response = new HashMap<>();
        if (userCharacterOpt.isPresent()) {
            UserCharactersEntitiy userCharacter = userCharacterOpt.get();

            if (!userCharacter.isOpen()) { // is_get이 false(0)라면
                userCharacter.setOpen(true); // true(1)로 변경
                userCharactersRepository.save(userCharacter); // 변경된 값 저장
                response.put("result", true);
            } else {
                response.put("result", true);
            }
        } else {
            response.put("result", null); // 해당 유저 캐릭터가 존재하지 않을 경우
        }
        return response;
    }
}

