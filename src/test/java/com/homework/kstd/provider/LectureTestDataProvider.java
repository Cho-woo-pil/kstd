package com.homework.kstd.provider;

import com.homework.kstd.entity.Lecture;

import java.time.LocalDateTime;
import java.util.UUID;

public class LectureTestDataProvider {

    public static Lecture createTestLecture() {
        return new Lecture("Test Speaker", UUID.randomUUID().toString(), "Test Lecture Content", 2, LocalDateTime.now(), 60);
    }

    public static Lecture createTestLectureWithId() {
        Lecture lecture = createTestLecture();
        lecture.setLectureId(UUID.randomUUID());  // Setting a random UUID for the test
        return lecture;
    }

}