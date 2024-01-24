package com.homework.kstd.service.lecture;

import com.homework.kstd.entity.Lecture;
import com.homework.kstd.entity.Venue;
import com.homework.kstd.presentor.LectureListPresenter;
import com.homework.kstd.repository.LectureRepository;
import com.homework.kstd.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetLectureListService {
    private final LectureRepository lectureRepository;
    private final VenueRepository venueRepository;

    public ResponseEntity<?> getUpcommingLectureList() {
        LocalDateTime oneWeekAfterNow = LocalDateTime.now().plusWeeks(1);
        LocalDateTime ondDayBeforeNow = LocalDateTime.now().minusDays(1);
        List<Lecture> upcomingLectures = lectureRepository.findByStartTimeBetween(ondDayBeforeNow, oneWeekAfterNow);
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


    private List<LectureListPresenter> convertToDto(List<Lecture> lectures) {
        return lectures.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LectureListPresenter convertToDto(Lecture lecture) {
        Optional<Venue> venueOptional = venueRepository.findById(UUID.fromString(lecture.getVenueId()));
        LectureListPresenter dto = new LectureListPresenter();
        dto.set강의ID(lecture.getLectureId().toString());
        dto.set강연자(lecture.getSpeaker());
        venueOptional.ifPresent(venue -> dto.set강연장(venue.getVenueName()));
        dto.set강연내용(lecture.getLectureContent());
        dto.set강의시작시간(lecture.getStartTime());
        dto.set강의시간(lecture.getDuration());
        dto.set총인원(lecture.getTotalParticipants());
        dto.set신청인원(lecture.getCurrentParticipants());

        return dto;
    }
}
