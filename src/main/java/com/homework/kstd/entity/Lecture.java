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

    private int currentParticipants = 0;

    @Column(length = 1000)
    private String lectureContent;

    private LocalDateTime startTime;

    private int duration; // 강연 시간 (분)

    private LocalDateTime endTime;

    private boolean isFull = false;

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

    public void incrementCurrentParticipants() {
        this.currentParticipants++;

        if (this.currentParticipants == this.totalParticipants) {
            setFull(true);
        }
    }

    public void decrementCurrentParticipants() {
        if (this.currentParticipants > 0) {
            this.currentParticipants--;

            // 강의가 가득 차 있을 때 강의 신청 취소로 인해 강의가 다시 참가 가능한 상태가 되도록 처리
            if (this.currentParticipants < this.totalParticipants) {
                setFull(false);
            }
        }

    }
    // Add a setter for isFull
    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

}
