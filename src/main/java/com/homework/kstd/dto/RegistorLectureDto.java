package com.homework.kstd.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RegistorLectureDto {
    private String speaker;
    private String venueId;
    private String lectureContent;
    private int totalParticipants;
    private LocalDateTime startTime;
    private int duration;
}
