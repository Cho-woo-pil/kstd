package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    // 테스트코드 해시 비교를 위한 메서드 정의
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureApplicationPresenter that = (LectureApplicationPresenter) o;
        return 강의시간 == that.강의시간 &&
                Objects.equals(강연ID, that.강연ID) &&
                Objects.equals(강연자, that.강연자) &&
                Objects.equals(강연장ID, that.강연장ID) &&
                Objects.equals(강연내용, that.강연내용) &&
                Objects.equals(강의시작시간, that.강의시작시간) &&
                Objects.equals(신청자목록, that.신청자목록);
    }

    @Override
    public int hashCode() {
        return Objects.hash(강연ID, 강연자, 강연장ID, 강연내용, 강의시작시간, 강의시간, 신청자목록);
    }

}
