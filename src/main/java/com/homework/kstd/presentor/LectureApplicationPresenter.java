package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class LectureApplicationPresenter {
    private String 강연ID;
    private String 강연자;
    private String 강연장ID;
    private String 강연내용;
    private LocalDateTime 강의시작시간;
    private int 강의시간;
    private List<EmployeeListPresenter> 신청자목록;

    public LectureApplicationPresenter(String 강연ID, String 강연자, String 강연장ID, String 강연내용,
                                       LocalDateTime 강의시작시간, int 강의시간, List<EmployeeListPresenter> 신청자목록) {
        this.강연ID = 강연ID;
        this.강연자 = 강연자;
        this.강연장ID = 강연장ID;
        this.강연내용 = 강연내용;
        this.강의시작시간 = 강의시작시간;
        this.강의시간 = 강의시간;
        this.신청자목록 = 신청자목록;
    }

}
