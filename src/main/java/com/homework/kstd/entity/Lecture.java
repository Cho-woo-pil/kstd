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
public class Lecture {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID lectureId;

    private String speaker;

    private String venueId;

    private int totalParticipants;

    private final int currentParticipants = 0;

    @Column(length = 1000)
    private String lectureContent;

    private LocalDateTime startTime;

    private int duration; // 강연 시간 (분)

    private LocalDateTime endTime;

    private final boolean isFull = false;

    public Lecture(String speaker, String venueId, String lectureContent, int totalParticipants, LocalDateTime startTime, int duration) {
        this.speaker = speaker;
        this.venueId = venueId;
        this.lectureContent = lectureContent;
        this.totalParticipants = totalParticipants;
        this.startTime = startTime;
        this.duration = duration;
        calculateEndTime();
    }

    private void calculateEndTime() {
        this.endTime = this.startTime.plusMinutes(this.duration);
    }
}
