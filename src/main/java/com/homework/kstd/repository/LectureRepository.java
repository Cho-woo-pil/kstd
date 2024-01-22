package com.homework.kstd.repository;

import com.homework.kstd.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface LectureRepository extends JpaRepository<Lecture, UUID> {
    @Query("SELECT COUNT(l) = 0 FROM Lecture l " +
            "WHERE l.venueId = :venueId " +
            "AND ((l.startTime >= :endTime AND l.endTime <= :startTime) " +
            "OR (l.endTime >= :startTime AND l.startTime <= :endTime))")
    boolean venueAvailable(String venueId, LocalDateTime startTime, LocalDateTime endTime);
}
