package com.homework.kstd.service.lecture;

import com.homework.kstd.entity.Lecture;
import com.homework.kstd.entity.Venue;
import com.homework.kstd.presentor.LectureListPresenter;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import com.homework.kstd.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetLectureListService {
    private final LectureRepository lectureRepository;
    private final VenueRepository venueRepository;
    private final LectureApplicationRepository lectureApplicationRepository;

    public ResponseEntity<?> getUpcommingLectureList() {
        List<Lecture> upcomingLectures = findUpcomingLectures();
        List<LectureListPresenter> upcomingLectureDtos = convertToDto(upcomingLectures);

        upcomingLectureDtos.sort(Comparator.comparing(LectureListPresenter::get신청인원));

        return ResponseEntity.ok(upcomingLectureDtos);
    }

    public ResponseEntity<?> getAllLectureList() {
        System.out.println(LocalDateTime.now());
        List<Lecture> allLecture = lectureRepository.findAll();
        List<LectureListPresenter> upcomingLectureDtos = convertToDto(allLecture);

        upcomingLectureDtos.sort(Comparator.comparing(LectureListPresenter::get강의시작시간));

        return ResponseEntity.ok(upcomingLectureDtos);
    }

    public ResponseEntity<?> getPopularLectureList() {
        LocalDateTime threeDaysBeforeNow = LocalDateTime.now().minusDays(3);
        LocalDateTime today = LocalDateTime.now();

        List<Lecture> upcomingLectures = findUpcomingLectures();

        // 3일 이내에 신청된 강의 수 조회
        List<Object[]> appliedCounts = lectureApplicationRepository.countLectureApplicationsWithin3Days(threeDaysBeforeNow, today);

        if (appliedCounts != null) {
        // 3일 이내에 신청된 강의 수를 매핑
        Map<String, Long> appliedCountsMap = appliedCounts.stream()
                .collect(Collectors.toMap(arr -> (String) arr[0], arr -> (Long) arr[1]));

        // appliedCountsMap을 기준으로 정렬
        upcomingLectures.sort(Comparator.comparing(lecture -> appliedCountsMap.getOrDefault(lecture.getLectureId().toString(), 0L), Comparator.reverseOrder()));}
        List<LectureListPresenter> upcomingLectureDtos = convertToDto(upcomingLectures);

        return ResponseEntity.ok(upcomingLectureDtos);
    }


    private List<LectureListPresenter> convertToDto(List<Lecture> lectures) {
        return lectures.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LectureListPresenter convertToDto(Lecture lecture) {
        Optional<Venue> venueOptional = venueRepository.findById(UUID.fromString(lecture.getVenueId()));
        return new LectureListPresenter(
                lecture.getLectureId().toString(),
                lecture.getSpeaker(),
                venueOptional.map(Venue::getVenueName).orElse(null),
                lecture.getLectureContent(),
                lecture.getStartTime(),
                lecture.getDuration(),
                lecture.getTotalParticipants(),
                lecture.getCurrentParticipants()
        );

    }
    private List<Lecture> findUpcomingLectures() {
        LocalDateTime oneWeekAfterNow = LocalDateTime.now().plusWeeks(1);
        LocalDateTime ondDayBeforeNow = LocalDateTime.now().minusDays(1);
        return lectureRepository.findByStartTimeBetween(ondDayBeforeNow, oneWeekAfterNow);
    }
}