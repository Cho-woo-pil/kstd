package com.homework.kstd.service.member;

import com.homework.kstd.dto.ApplyLectureDto;
import com.homework.kstd.entity.Lecture;
import com.homework.kstd.entity.LectureApplication;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplyLectureService{
    private final LectureRepository lectureRepository;
    private final LectureApplicationRepository lectureApplicationRepository;

    @Transactional
    public ResponseEntity<?> applyLecture(ApplyLectureDto dto) {
        try {
            if (dto.getEmployeeId() == null || dto.getEmployeeId().length() != 5) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사원번호는 5글자여야 합니다.");
            }

            Optional<Lecture> lectureOptional = lectureRepository.findById(UUID.fromString(dto.getLectureId()));
            if (lectureOptional.isPresent()) {
                Lecture lecture = lectureOptional.get();

                // 중복 신청 확인
                if (lectureApplicationRepository.aleadyApplyLecture(dto.getLectureId(), dto.getEmployeeId())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 신청한 강의입니다.");
                }
                // 총인원과 현재인원 확인
                if (lecture.isFull()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신청 인원이 가득 찼습니다.");
                }
                // 동시성 제어를 위한 버전 업데이트
                lecture = lectureRepository.findById(UUID.fromString(dto.getLectureId()))
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강연입니다."));

                lecture.incrementCurrentParticipants();

                // 강연 엔터티 업데이트
                lectureRepository.save(lecture);
                // 강연 신청 엔터티 생성
                LectureApplication application = new LectureApplication(dto.getLectureId(), dto.getEmployeeId());
                lectureApplicationRepository.save(application);

                return ResponseEntity.ok("강의 신청이 완료되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 강의입니다.");
            }
        } catch (OptimisticLockingFailureException ex) {
            // 동시성 문제로 인한 예외 처리
            return ResponseEntity.status(HttpStatus.CONFLICT).body("강의 신청 중 오류가 발생 했습니다. 잠시 후 다시 시도해주세요.");
        } catch (Exception e) {
            // 그 외 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
}

