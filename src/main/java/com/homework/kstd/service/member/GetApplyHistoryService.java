package com.homework.kstd.service.member;

import com.homework.kstd.entity.LectureApplication;
import com.homework.kstd.presentor.ApplicationHistoryPresenter;
import com.homework.kstd.repository.LectureApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetApplyHistoryService {
    private final LectureApplicationRepository lectureApplicationRepository;

    public ResponseEntity<?> getApplyHistory(String employeeId) {
        List<LectureApplication> lectureApplications = lectureApplicationRepository.findByEmployeeIdAndIsDeleted(employeeId, false);
        List<ApplicationHistoryPresenter> applicationHistoryPresenters = lectureApplications.stream()
                .map(this::convertToPresenter)
                .collect(Collectors.toList());

        return ResponseEntity.ok(applicationHistoryPresenters);
    }

    private ApplicationHistoryPresenter convertToPresenter(LectureApplication lectureApplication) {
        ApplicationHistoryPresenter presenter = new ApplicationHistoryPresenter(lectureApplication.getLectureId(), lectureApplication.getAppliedAt());
        return presenter;
    }

}
