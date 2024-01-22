package com.homework.kstd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID lectureId;

    private String speaker;

    private String venueId;

    private int totalParticipants;

    private int currentParticipants;

    @Column(length = 1000)
    private String lectureContent;

    private LocalDateTime startTime;

    private int duration; // 강연 시간 (분)

    private LocalDateTime endTime;

    private boolean isFull;
}
