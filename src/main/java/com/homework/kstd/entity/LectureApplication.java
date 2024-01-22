package com.homework.kstd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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

    @Version
    private Long version;

    public LectureApplication(String lectureId, String employeeId) {
        this.lectureId = lectureId;
        this.employeeId = employeeId;
    }
}