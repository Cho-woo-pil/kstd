package com.homework.kstd.service.member;

import com.homework.kstd.dto.ApplyLectureDto;
import com.homework.kstd.entity.Lecture;
import com.homework.kstd.entity.LectureApplication;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancleLectureService {
    private final LectureRepository lectureRepository;
    private final LectureApplicationRepository lectureApplicationRepository;

    @Transactional
    public ResponseEntity<?> cancelLecture(ApplyLectureDto dto) {
        try {
            if (dto.getEmployeeId() == null || dto.getEmployeeId().length() != 5) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사원번호는 5글자여야 합니다.");
            }

            Optional<LectureApplication> applicationOptional = lectureApplicationRepository.findLectureApplication(dto.getLectureId(), dto.getEmployeeId());
            if (applicationOptional.isPresent()) {
                Optional<Lecture> lectureOptional = lectureRepository.findById(UUID.fromString(dto.getLectureId()));
                if (lectureOptional.isPresent()) {
                    Lecture lecture = lectureOptional.get();

                    // 동시성 제어를 위한 버전 업데이트
                    lecture = lectureRepository.findById(UUID.fromString(dto.getLectureId()))
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강연입니다."));

                    // 강의 및 신청 취소
                    lecture.decrementCurrentParticipants();
                    applicationOptional.get().cancel(); // LectureApplication 취소 표시

                    // 강연 엔터티 업데이트
                    lectureRepository.save(lecture);

                    // 신청 취소 엔터티 저장
                    lectureApplicationRepository.save(applicationOptional.get());

                    return ResponseEntity.ok("강의 신청이 취소되었습니다.");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 강의입니다.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 취소됐거나 신청하지 않은 강의입니다.");
            }
        } catch (Exception e) {
            // 그 외 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
}