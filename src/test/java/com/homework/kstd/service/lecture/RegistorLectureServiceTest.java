package com.homework.kstd.service.lecture;

import com.homework.kstd.dto.RegistorLectureDto;
import com.homework.kstd.entity.Venue;
import com.homework.kstd.provider.VenueTestDataProvider;
import com.homework.kstd.repository.LectureRepository;
import com.homework.kstd.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RegistorLectureServiceTest {
    @Test
    void registorLecture_Success() {
        // Repository를 Mock으로 대체
        LectureRepository lectureRepository = Mockito.mock(LectureRepository.class);
        VenueRepository venueRepository = Mockito.mock(VenueRepository.class);

        // 테스트 데이터
        RegistorLectureDto dto = new RegistorLectureDto();
        dto.setSpeaker("TestSpeaker");
        dto.setVenueId(UUID.randomUUID().toString());
        dto.setLectureContent("TestLectureContent");
        dto.setTotalParticipants(50);
        dto.setStartTime(LocalDateTime.now());
        dto.setDuration(60);

        Venue venue = VenueTestDataProvider.createVenueWithId("Test Venue");
        venue.setVenue(UUID.randomUUID()); // Set a non-null UUID for the lecture


        // Mock 동작 설정
        when(venueRepository.findById(any())).thenReturn(Optional.of(venue));
        when(lectureRepository.venueAvailable(any(), any(), any())).thenReturn(true);

        // 서비스 생성
        RegistorLectureService registorLectureService = new RegistorLectureService(lectureRepository, venueRepository);

        // 서비스 호출
        ResponseEntity<?> responseEntity = registorLectureService.registorLecture(dto);

        // 결과 확인
        assertEquals(ResponseEntity.ok("강의가 등록되었습니다."), responseEntity);
    }

    @Test
    void registorLecture_VenueNotFound() {
        // Repository를 Mock으로 대체
        LectureRepository lectureRepository = Mockito.mock(LectureRepository.class);
        VenueRepository venueRepository = Mockito.mock(VenueRepository.class);

        // 테스트 데이터
        RegistorLectureDto dto = new RegistorLectureDto();
        dto.setSpeaker("TestSpeaker");
        dto.setVenueId(UUID.randomUUID().toString());
        dto.setLectureContent("TestLectureContent");
        dto.setTotalParticipants(50);
        dto.setStartTime(LocalDateTime.now());
        dto.setDuration(60);

        // Mock 동작 설정
        when(venueRepository.findById(any())).thenReturn(Optional.empty());

        // 서비스 생성
        RegistorLectureService registorLectureService = new RegistorLectureService(lectureRepository, venueRepository);

        // 서비스 호출
        ResponseEntity<?> responseEntity = registorLectureService.registorLecture(dto);

        // 결과 확인
        assertEquals(ResponseEntity.status(404).body("없는 강의실 입니다."), responseEntity);
    }
}
