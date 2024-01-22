package com.homework.kstd.repository;

import com.homework.kstd.entity.LectureApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LectureApplicationRepository extends JpaRepository<LectureApplication, UUID> {
}