package com.homework.kstd.service.lecture;

import com.homework.kstd.dto.RegistorLectureDto;
import com.homework.kstd.entity.Lecture;
import com.homework.kstd.provider.LectureTestDataProvider;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class GetLectureApplicationListServiceTest {

    @Test
    void getLectureApplicationList_Success() {
        // Repository를 Mock으로 대체
        LectureRepository lectureRepository = Mockito.mock(LectureRepository.class);
        LectureApplicationRepository lectureApplicationRepository = Mockito.mock(LectureApplicationRepository.class);

        // 테스트 데이터
        Lecture lecture1 = LectureTestDataProvider.createTestLectureWithId();
        Lecture lecture2 = LectureTestDataProvider.createTestLectureWithId();

        List<Lecture> lectureList = List.of(lecture1, lecture2);

        // Mock 동작 설정
        when(lectureRepository.findAll()).thenReturn(lectureList);
        when(lectureApplicationRepository.findByLectureId(any())).thenReturn(new ArrayList<>());

        // 서비스 생성
        GetLectureApplicationListService service = new GetLectureApplicationListService(lectureApplicationRepository, lectureRepository);

        // 서비스 호출
        ResponseEntity<?> responseEntity = service.getLectureApplicationList();

        // 결과 확인
        assertEquals(ResponseEntity.ok(lectureList.stream().map(service::convertToPresenter).toList()), responseEntity);
    }

    @Test
    void getLectureApplication_Success() {
        // Repository를 Mock으로 대체
        LectureRepository lectureRepository = Mockito.mock(LectureRepository.class);
        LectureApplicationRepository lectureApplicationRepository = Mockito.mock(LectureApplicationRepository.class);

        // 테스트 데이터
        Lecture lecture = LectureTestDataProvider.createTestLectureWithId();
        UUID lectureId = lecture.getLectureId();

        // Mock 동작 설정
        when(lectureRepository.findByLectureId(lectureId)).thenReturn(List.of(lecture));
        when(lectureApplicationRepository.findByLectureId(lectureId.toString())).thenReturn(new ArrayList<>());

        // 서비스 생성
        GetLectureApplicationListService service = new GetLectureApplicationListService(lectureApplicationRepository, lectureRepository);

        // 서비스 호출
        ResponseEntity<?> responseEntity = service.getLectureApplication(lectureId.toString());

        // 결과 확인
        assertEquals(ResponseEntity.ok(List.of(service.convertToPresenter(lecture))), responseEntity);
    }
}
