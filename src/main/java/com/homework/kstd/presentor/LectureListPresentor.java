package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class LectureListPresentor {
    private String 강의ID;
    private String 강연자;
    private String 강연장;
    private String 강연내용;
    private LocalDateTime 강의시작시간;
    private int 강의시간;
    private int 총인원;
    private int 신청인원;

}
