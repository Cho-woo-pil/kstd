package com.homework.kstd.provider;

import com.homework.kstd.entity.LectureApplication;

import java.time.LocalDateTime;
import java.util.UUID;

public class LectureApplicationTestDataProvider {

    public static LectureApplication createTestLectureApplication() {
        return new LectureApplication(UUID.randomUUID().toString(), "00001");
    }

    public static LectureApplication createTestLectureApplicationWithId() {
        LectureApplication application = createTestLectureApplication();
        return application;
    }
}