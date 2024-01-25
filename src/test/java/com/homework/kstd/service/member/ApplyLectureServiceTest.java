package com.homework.kstd.service.member;

import com.homework.kstd.dto.ApplyLectureDto;
import com.homework.kstd.entity.Lecture;

import com.homework.kstd.provider.LectureTestDataProvider;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ApplyLectureServiceTest {

    @Test
    void applyLecture_NormalRequest_Success() {
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
        // Mock 객체의 동작 설정
        when(lectureRepository.findById(any())).thenReturn(Optional.of(testLecture));
        when(lectureApplicationRepository.aleadyApplyLecture(any(), any())).thenReturn(false);

        // 테스트할 서비스 생성
        ApplyLectureService applyLectureService = new ApplyLectureService(lectureRepository, lectureApplicationRepository);

        // 서비스 실행
        ResponseEntity<?> responseEntity = applyLectureService.applyLecture(dto);

        // 결과 확인
        assertEquals(ResponseEntity.ok("강의 신청이 완료되었습니다."), responseEntity);
        assertEquals(1, testLecture.getCurrentParticipants());
    }

    @Test
    void applyLecture_DuplicateRequest_Failure() {
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

        // Mock 객체의 동작 설정
        when(lectureRepository.findById(any())).thenReturn(Optional.of(testLecture));
        when(lectureApplicationRepository.aleadyApplyLecture(any(), any())).thenReturn(true); // 이미 신청한 상태

        // 테스트할 서비스 생성
        ApplyLectureService applyLectureService = new ApplyLectureService(lectureRepository, lectureApplicationRepository);

        // 서비스 실행
        ResponseEntity<?> responseEntity = applyLectureService.applyLecture(dto);

        // 결과 확인
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 신청한 강의입니다."), responseEntity);
        assertEquals(0, testLecture.getCurrentParticipants()); // 참가자 수는 증가하지 않아야 함
    }

    @Test
    void applyLecture_FullCapacity_Failure() {
        // Mock 객체 생성
        LectureRepository lectureRepository = Mockito.mock(LectureRepository.class);
        LectureApplicationRepository lectureApplicationRepository = Mockito.mock(LectureApplicationRepository.class);

        // 테스트용 Lecture 객체 생성
        Lecture testLecture = LectureTestDataProvider.createTestLectureWithId();
        testLecture.setLectureId(UUID.randomUUID()); // Set a non-null UUID for the lecture
        testLecture.incrementCurrentParticipants();
        testLecture.incrementCurrentParticipants();

        // 테스트용 ApplyLectureDto 생성
        ApplyLectureDto dto = new ApplyLectureDto();
        dto.setLectureId(testLecture.getLectureId().toString());
        dto.setEmployeeId("00002"); // Another employee ID

        // Mock 객체의 동작 설정
        when(lectureRepository.findById(any())).thenReturn(Optional.of(testLecture));
        when(lectureApplicationRepository.aleadyApplyLecture(any(), any())).thenReturn(false);

        // 테스트할 서비스 생성
        ApplyLectureService applyLectureService = new ApplyLectureService(lectureRepository, lectureApplicationRepository);

        // 첫 번째 신청
        applyLectureService.applyLecture(dto);

        // 두 번째 신청
        ResponseEntity<?> responseEntity = applyLectureService.applyLecture(dto);

        // 결과 확인
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신청 인원이 가득 찼습니다."), responseEntity);
    }

    // 다른 테스트 케이스들도 추가 가능
}