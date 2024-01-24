package com.homework.kstd.service.member;

import com.homework.kstd.entity.LectureApplication;
import com.homework.kstd.entity.Venue;
import com.homework.kstd.presentor.ApplicationHistoryPresenter;
import com.homework.kstd.provider.LectureApplicationTestDataProvider;
import com.homework.kstd.repository.LectureApplicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetApplyHistoryServiceTest {

    @Test
    void getApplyHistory_Success() {
        // Mock 객체 생성
        LectureApplicationRepository lectureApplicationRepository = Mockito.mock(LectureApplicationRepository.class);

        // 테스트용 LectureApplication 리스트 객체 생성
        List<LectureApplication> mockApplications = Arrays.asList(
                LectureApplicationTestDataProvider.createTestLectureApplicationWithId(),
                LectureApplicationTestDataProvider.createTestLectureApplicationWithId()
        );

        when(lectureApplicationRepository.findByEmployeeIdAndIsDeleted("00001", false)).thenReturn(mockApplications);

        // 테스트할 서비스 생성
        GetApplyHistoryService getApplyHistoryService = new GetApplyHistoryService(lectureApplicationRepository);

        // 서비스 실행
        ResponseEntity<?> responseEntity = getApplyHistoryService.getApplyHistory("00001");

        // 예상 결과 생성
        List<ApplicationHistoryPresenter> expected = Arrays.asList(
                new ApplicationHistoryPresenter(mockApplications.get(0).getLectureId(), mockApplications.get(0).getAppliedAt()),
                new ApplicationHistoryPresenter(mockApplications.get(1).getLectureId(), mockApplications.get(1).getAppliedAt())
        );

        // 실제 결과 가져오기
        List<ApplicationHistoryPresenter> actual = (List<ApplicationHistoryPresenter>) responseEntity.getBody();

        // 결과 확인
        assertEquals(expected, actual);
    }

}
