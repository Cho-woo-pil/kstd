package com.homework.kstd.service.lecture;

import com.homework.kstd.entity.Lecture;
import com.homework.kstd.entity.Venue;
import com.homework.kstd.presentor.LectureListPresenter;
import com.homework.kstd.provider.LectureTestDataProvider;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import com.homework.kstd.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetLectureListServiceTest {
    // Mock repositories
    LectureRepository lectureRepository = Mockito.mock(LectureRepository.class);
    VenueRepository venueRepository = Mockito.mock(VenueRepository.class);
    LectureApplicationRepository lectureApplicationRepository = Mockito.mock(LectureApplicationRepository.class);

    @Test
    void getUpcomingLectureList_Success() {
        // 테스트 데이터
        LocalDateTime now = LocalDateTime.now();
        Lecture lecture1 = LectureTestDataProvider.createTestLectureWithId();
        Lecture lecture2 = LectureTestDataProvider.createTestLectureWithId();
        List<Lecture> upcomingLectures = List.of(lecture1, lecture2);

        // Mock 동작 설정
        when(lectureRepository.findByStartTimeBetween(Mockito.any(), Mockito.any())).thenReturn(upcomingLectures);
        when(venueRepository.findById(UUID.fromString(upcomingLectures.get(0).getVenueId())))
                .thenReturn(Optional.of(new Venue("장소 1")));

        when(venueRepository.findById(UUID.fromString(upcomingLectures.get(1).getVenueId())))
                .thenReturn(Optional.of(new Venue("장소 2")));

        // 서비스 생성
        GetLectureListService service = new GetLectureListService(lectureRepository, venueRepository, lectureApplicationRepository);

        // 서비스 호출
        ResponseEntity<?> responseEntity = service.getUpcommingLectureList();

        // 결과 확인
        List<LectureListPresenter> expectedList = List.of(
                new LectureListPresenter(upcomingLectures.get(0).getLectureId().toString(), lecture1.getSpeaker(), "장소 1", lecture1.getLectureContent(), lecture1.getStartTime(), lecture1.getDuration(), lecture1.getTotalParticipants(), lecture1.getCurrentParticipants()),
                new LectureListPresenter(upcomingLectures.get(1).getLectureId().toString(), lecture2.getSpeaker(), "장소 2", lecture2.getLectureContent(), lecture2.getStartTime(), lecture2.getDuration(), lecture2.getTotalParticipants(), lecture2.getCurrentParticipants())
        );
        List<LectureListPresenter> actual = (List<LectureListPresenter>) responseEntity.getBody();

        // assertThat 사용
        assertEquals(expectedList, actual);
    }

    @Test
    void getAllLectureList_Success() {
        // 테스트 데이터
        List<Lecture> allLectures = List.of(
                LectureTestDataProvider.createTestLectureWithId(),
                LectureTestDataProvider.createTestLectureWithId()
        );

        // Mock 동작 설정
        when(lectureRepository.findAll()).thenReturn(allLectures);
        when(venueRepository.findById(UUID.fromString(allLectures.get(0).getVenueId())))
                .thenReturn(Optional.of(new Venue("장소 1")));
        when(venueRepository.findById(UUID.fromString(allLectures.get(1).getVenueId())))
                .thenReturn(Optional.of(new Venue("장소 2")));

        // 서비스 생성
        GetLectureListService service = new GetLectureListService(lectureRepository, venueRepository, lectureApplicationRepository);

        // 서비스 호출
        ResponseEntity<?> responseEntity = service.getAllLectureList();

        // 결과 확인
        List<LectureListPresenter> expectedList = List.of(
                new LectureListPresenter(allLectures.get(0).getLectureId().toString(), allLectures.get(0).getSpeaker(), "장소 1", allLectures.get(0).getLectureContent(), allLectures.get(0).getStartTime(), allLectures.get(0).getDuration(), allLectures.get(0).getTotalParticipants(), allLectures.get(0).getCurrentParticipants()),
                new LectureListPresenter(allLectures.get(1).getLectureId().toString(), allLectures.get(1).getSpeaker(), "장소 2", allLectures.get(1).getLectureContent(), allLectures.get(1).getStartTime(), allLectures.get(1).getDuration(), allLectures.get(1).getTotalParticipants(), allLectures.get(1).getCurrentParticipants())
        );
        List<LectureListPresenter> actual = (List<LectureListPresenter>) responseEntity.getBody();

        assertEquals(expectedList, actual);
    }

    @Test
    void getPopularLectureList_Success() {
        // 테스트 데이터
        List<Lecture> upcomingLectures = List.of(
                LectureTestDataProvider.createTestLectureWithId(),
                LectureTestDataProvider.createTestLectureWithId()
        );

        // Mock 동작 설정
        when(lectureRepository.findByStartTimeBetween(Mockito.any(), Mockito.any())).thenReturn(upcomingLectures);
        when(venueRepository.findById(UUID.fromString(upcomingLectures.get(0).getVenueId())))
                .thenReturn(Optional.of(new Venue("장소 1")));
        when(venueRepository.findById(UUID.fromString(upcomingLectures.get(1).getVenueId())))
                .thenReturn(Optional.of(new Venue("장소 2")));

        when(lectureApplicationRepository.countLectureApplicationsWithin3Days(Mockito.any(), Mockito.any()))
                .thenAnswer(invocation -> {
                    return null; // 또는 다른 값을 리턴하거나 예외를 던질 수 있음
                });

        // 서비스 생성
        GetLectureListService service = new GetLectureListService(lectureRepository, venueRepository, lectureApplicationRepository);

        // 서비스 호출
        ResponseEntity<?> responseEntity = service.getPopularLectureList();

        // 결과 확인
        List<LectureListPresenter> expectedList = List.of(
                new LectureListPresenter(upcomingLectures.get(0).getLectureId().toString(), upcomingLectures.get(0).getSpeaker(), "장소 1", upcomingLectures.get(0).getLectureContent(), upcomingLectures.get(0).getStartTime(), upcomingLectures.get(0).getDuration(), upcomingLectures.get(0).getTotalParticipants(), upcomingLectures.get(0).getCurrentParticipants()),
                new LectureListPresenter(upcomingLectures.get(1).getLectureId().toString(), upcomingLectures.get(1).getSpeaker(), "장소 2", upcomingLectures.get(1).getLectureContent(), upcomingLectures.get(1).getStartTime(), upcomingLectures.get(1).getDuration(), upcomingLectures.get(1).getTotalParticipants(), upcomingLectures.get(1).getCurrentParticipants())
        );
        List<LectureListPresenter> actual = (List<LectureListPresenter>) responseEntity.getBody();

        assertEquals(expectedList, actual);
    }
}
