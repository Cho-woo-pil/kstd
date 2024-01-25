package com.homework.kstd.service.member;

import com.homework.kstd.dto.ApplyLectureDto;
import com.homework.kstd.entity.Lecture;
import com.homework.kstd.entity.LectureApplication;
import com.homework.kstd.provider.LectureTestDataProvider;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CancleLectureServiceTest {

    @Test
    void cancelLecture_NormalCancellation_Success() {
        // Mock 객체 생성
        LectureRepository lectureRepository = Mockito.mock(LectureRepository.class);
        LectureApplicationRepository lectureApplicationRepository = Mockito.mock(LectureApplicationRepository.class);

        // 테스트용 Lecture 객체 생성
        Lecture testLecture = LectureTestDataProvider.createTestLectureWithId();
        testLecture.setLectureId(UUID.randomUUID()); // Set a non-null UUID for the lecture

        // 테스트용 ApplyLectureDto 생성
        ApplyLectureDto dto = new ApplyLectureDto();
        dto.setLectureId(testLecture.getLectureId().toString());
        dto.setEmployeeId("00001");

        // 테스트용 LectureApplication 객체 생성
        LectureApplication testApplication = new LectureApplication(dto.getLectureId(), dto.getEmployeeId());

        // Mock 객체의 동작 설정
        when(lectureRepository.findById(any())).thenReturn(Optional.of(testLecture));
        when(lectureApplicationRepository.findLectureApplication(any(), any())).thenReturn(Optional.of(testApplication));

        // 테스트할 서비스 생성
        CancleLectureService cancelLectureService = new CancleLectureService(lectureRepository, lectureApplicationRepository);

        // 서비스 실행
        ResponseEntity<?> responseEntity = cancelLectureService.cancelLecture(dto);

        // 결과 확인
        assertEquals(ResponseEntity.ok("강의 신청이 취소되었습니다."), responseEntity);
        assertTrue(testApplication.isDeleted()); // LectureApplication의 취소 상태가 true여야 함
    }

    // 다른 테스트 케이스들도 추가 가능
}
