package com.oru.oruerakproject.records;

import com.oru.oruerakproject.records.dto.RatingsDto;
import com.oru.oruerakproject.records.dto.RecordsDto;
import com.oru.oruerakproject.records.entity.RecordsEntity;
import com.oru.oruerakproject.records.repository.RecordsRepository;
import com.oru.oruerakproject.records.service.recordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/hiking")
public class recordsController {
    private final recordsService recordsService;
    private final RecordsRepository recordsRepository;
    // 등산정보 기입
    @PostMapping
    public ResponseEntity<String> createHikingRecord(@RequestBody RecordsDto recordsDto) {
        recordsService.record(recordsDto);
        return ResponseEntity.ok("등산정보가 성공적으로 기입되었습니다.");
    }
    // 최근 등산정보 불러오기
    @GetMapping("/{userId}/recent")
    public ResponseEntity<RecordsDto> getRecentHikingRecords(@PathVariable String userId) {
        RecordsDto recordsDto = recordsService.load(userId);

        if (recordsDto != null) {
            return ResponseEntity.ok(recordsDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // 모든 등산정보 불러오기
    @GetMapping("/{userId}/all")
    public ResponseEntity<Page<RecordsDto>> getAllHikingRecords(
            @PathVariable String userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int limit) {
        Page<RecordsDto> records = recordsService.getAllHikingRecords(userId, page, limit);
        return ResponseEntity.ok(records);
    }

    // 특정 등산정보 불러오기
    @GetMapping("/{userId}/{id}")
    public ResponseEntity<RecordsDto> getHikingRecordById(
            @PathVariable String userId,
            @PathVariable Long id) {
        RecordsDto record = recordsService.getHikingRecordById(userId, id);
        return ResponseEntity.ok(record);
    }
    // 글 id의 별점 정보 불러오기
    @GetMapping("/rating/load/{id}")
    public ResponseEntity<RatingsDto> getHikingRecordRating(@PathVariable Long id) {
        RatingsDto records = recordsService.getRecordRating(id);
        return ResponseEntity.ok(records);
    }
    // 글 id의 별점 정보 기입하기
    @PutMapping("/rating/save/{id}")
    public ResponseEntity createRating(
            @PathVariable Long id,
            @RequestBody RatingsDto dto) {
        entity = recordsService.saveRating(dto);
        return ResponseEntity.ok(entity);
    }


}


