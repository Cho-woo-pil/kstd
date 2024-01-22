package com.homework.kstd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class LectureApplication {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID applicationId;

    private String lectureId;

    private String employeeId;

    private boolean isDeleted = false;

    private LocalDateTime appliedAt;

    @Version
    private Long version;

    public LectureApplication(String lectureId, String employeeId) {
        this.lectureId = lectureId;
        this.employeeId = employeeId;
        this.appliedAt = LocalDateTime.now(); // 현재 시간을 기록
    }

    public void cancel() {
        this.isDeleted = true;
    }
}