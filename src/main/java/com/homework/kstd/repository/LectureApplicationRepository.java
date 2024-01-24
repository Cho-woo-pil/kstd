package com.homework.kstd.repository;

import com.homework.kstd.entity.LectureApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LectureApplicationRepository extends JpaRepository<LectureApplication, UUID> {
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LectureApplication l " +
            "WHERE l.lectureId = :lectureId AND l.employeeId = :employeeId AND l.isDeleted = false")
    boolean aleadyApplyLecture(String lectureId, String employeeId);

    @Query("SELECT l FROM LectureApplication l " +
            "WHERE l.lectureId = :lectureId AND l.employeeId = :employeeId AND l.isDeleted = false")
    Optional<LectureApplication> findLectureApplication(String lectureId, String employeeId);

    Collection<LectureApplication> findByLectureId(String lectureId);

    List<LectureApplication> findByEmployeeIdAndIsDeleted(String employeeId, boolean isDeleted);

}