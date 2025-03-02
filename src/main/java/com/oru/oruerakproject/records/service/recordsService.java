package com.oru.oruerakproject.records.service;

import com.oru.oruerakproject.records.repository.RecordsRepository;
import com.oru.oruerakproject.records.dto.RecordsDto;
import com.oru.oruerakproject.records.entity.RecordsEntity;
import com.oru.oruerakproject.unity.entitiy.CharactersEntitiy;
import com.oru.oruerakproject.unity.entitiy.UserCharactersEntitiy;
import com.oru.oruerakproject.unity.repository.CharactersRepository;
import com.oru.oruerakproject.unity.repository.UserCharactersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class recordsService {
    @Autowired
    private RecordsRepository recordsRepository;
    @Autowired
    private UserCharactersRepository userCharacterRepository;

    @Autowired
    private CharactersRepository characterRepository;

    //DTO -> Entity 변환해서 Entity 저장
    public void record(RecordsDto recordDto) {
        RecordsEntity recordsconvEntity = RecordsDto.toEntity(recordDto);
        recordsRepository.save(recordsconvEntity);
        addCharacterToUser(recordDto.getUserId(), recordDto.getMountainName());
    }
    // 등산한 산에 해당하는 캐릭터를 유저에게 추가
    private void addCharacterToUser(String userId, String mountainName) {
        Optional<CharactersEntitiy> characterOpt = characterRepository.findBymountainName(mountainName);
        if (characterOpt.isPresent()) {
            int characterId = characterOpt.get().getCharacterId();
            boolean alreadyExists = userCharacterRepository
                    .findByUserIdAndCharacterId(userId, characterId)
                    .isPresent();
            if (!alreadyExists) {
                userCharacterRepository.save(
                        UserCharactersEntitiy.builder()
                                .userId(userId)
                                .characterId(characterId)
                                .open(false)
                                .build()
                );
            }
        }
    }

    // 최근 등산정보 불러오기 api
    // 최신 등산 기록 가져와서 dto 변환
    // 유저아이디(닉네임) 받아서 해당 유저아이디의 가장 최근 등산 기록을 엔티티에서 가져오고
    // 가져온 엔티티데이터를 DTO로 변환해서 리턴
    public RecordsDto load(String userId) {
        RecordsEntity recordsEntity =  recordsRepository.findTopByUserIdOrderByHikeDateDesc(userId);
        return recordsEntity != null ? RecordsDto.fromEntity(recordsEntity) : null;
    }
    //모든 등산정보 불러오기 Service
    // 정해진 페이지, 리미트, 유저 아이디 받아서 해당 userId의 page의 limit 만큼 엔티티에서 값 가져옴
    // 가져온 Entitiy -> DTO로 변환해서 리턴
    public Page<RecordsDto> getAllHikingRecords(String userId, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit); // JPA의 페이지는 0부터 시작
        Page<RecordsEntity> hikingPage = recordsRepository.findByUserIdOrderByHikeDateDesc(userId, pageable);
        return hikingPage.map(RecordsDto::fromEntity); // Page 유지
    }

    public RecordsDto getHikingRecordById(String userId, Long id) {
        return recordsRepository.findByIdAndUserId(id, userId)
                .map(RecordsDto::fromEntity)  // Optional 내부 값을 DTO로 변환
                .orElse(null); // 값이 없으면 null 반환
    }

}
