package com.homework.kstd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private boolean isDeleted;
}