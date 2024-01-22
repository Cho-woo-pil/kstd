package com.homework.kstd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class LectureApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID applicationId;

    private String lectureId;

    private String employeeId;

    private boolean isDeleted;
}