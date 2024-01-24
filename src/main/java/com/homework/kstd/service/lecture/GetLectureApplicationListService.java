package com.homework.kstd.service.lecture;

import com.homework.kstd.entity.Lecture;
import com.homework.kstd.presentor.EmployeeListPresenter;
import com.homework.kstd.presentor.LectureApplicationPresenter;
import com.homework.kstd.repository.LectureApplicationRepository;
import com.homework.kstd.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetLectureApplicationListService {

    private final LectureApplicationRepository lectureApplicationRepository;
    private final LectureRepository lectureRepository;

    public ResponseEntity<?> getLectureApplicationList() {
        List<LectureApplicationPresenter> lectureApplicationPresenterList = lectureRepository.findAll().stream()
                .map(this::convertToPresenter)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lectureApplicationPresenterList);
    }

    public ResponseEntity<?> getLectureApplication(String lectureId) {
        List<LectureApplicationPresenter> lectureApplicationPresenter = lectureRepository.findByLectureId(UUID.fromString(lectureId)).stream()
                .map(this::convertToPresenter)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lectureApplicationPresenter);
    }

    private LectureApplicationPresenter convertToPresenter(Lecture lecture) {

        List<EmployeeListPresenter> employeeListPresenters = lectureApplicationRepository.findByLectureId(lecture.getLectureId().toString()).stream()
                .filter(lectureApplication -> !lectureApplication.isDeleted())
                .map(lectureApplication -> new EmployeeListPresenter(lectureApplication.getEmployeeId(), lectureApplication.getAppliedAt()))
                .toList();
        return new LectureApplicationPresenter(
                lecture.getLectureId().toString(),
                lecture.getSpeaker(),
                lecture.getVenueId(),
                lecture.getLectureContent(),
                lecture.getStartTime(),
                lecture.getDuration(),
                employeeListPresenters
        );
    }


}
