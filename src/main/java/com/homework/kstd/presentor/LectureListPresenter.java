package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LectureListPresenter {
    private String 강의ID;
    private String 강연자;
    private String 강연장;
    private String 강연내용;
    private LocalDateTime 강의시작시간;
    private int 강의시간;
    private int 총인원;
    private int 신청인원;

    public LectureListPresenter(
            String 강의ID, String 강연자, String 강연장, String 강연내용,
            LocalDateTime 강의시작시간, int 강의시간, int 총인원, int 신청인원) {
        this.강의ID = 강의ID;
        this.강연자 = 강연자;
        this.강연장 = 강연장;
        this.강연내용 = 강연내용;
        this.강의시작시간 = 강의시작시간;
        this.강의시간 = 강의시간;
        this.총인원 = 총인원;
        this.신청인원 = 신청인원;
    }
}
