package com.homework.kstd.service.lecture;

import com.homework.kstd.dto.RegistorLectureDto;
import com.homework.kstd.entity.Lecture;
import com.homework.kstd.entity.Venue;
import com.homework.kstd.repository.LectureRepository;
import com.homework.kstd.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistorLectureService {
    private final LectureRepository lectureRepository;
    private final VenueRepository venueRepository;

    @Transactional
    public ResponseEntity<?> registorLecture(RegistorLectureDto dto){

        Optional<Venue> venueOptional = venueRepository.findById(UUID.fromString(dto.getVenueId()));

        if (venueOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("없는 강의실 입니다.");
        }

        Venue venue = venueOptional.get();

        // 해당 강의실이 사용 중인지 확인
        if (!isVenueInUse(venue, dto.getStartTime(), dto.getDuration())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 강의실은 이미 사용 중입니다.");
        }

        // RegistorLectureDto에서 Lecture 엔터티로 변환
        Lecture lecture = new Lecture(
                dto.getSpeaker(),
                dto.getVenueId(),
                dto.getLectureContent(),
                dto.getTotalParticipants(),
                dto.getStartTime(),
                dto.getDuration()
        );

        // Lecture 저장
        lectureRepository.save(lecture);
        return ResponseEntity.ok("강의가 등록되었습니다.");
    }

    private boolean isVenueInUse(Venue venue, LocalDateTime startTime, int duration) {
        // 강의실이 사용 중인지 로직 구현
        // 예를 들어, 이미 등록된 강의 중에 시간이 겹치는 경우 등을 확인할 수 있습니다.
        // 이 예시에서는 간단히 startTime과 endTime을 사용하여 강의 시간 중복 여부를 확인합니다.
        LocalDateTime endTime = startTime.plusMinutes(duration);

        return lectureRepository.venueAvailable(
                venue.getVenueId().toString(), startTime, endTime);
    }

}
