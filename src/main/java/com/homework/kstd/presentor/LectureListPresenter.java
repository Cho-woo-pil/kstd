package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Objects;

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

    // 테스트코드 해시 비교를 위한 메서드 정의
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureListPresenter that = (LectureListPresenter) o;
        return Objects.equals(강의ID, that.강의ID) &&
                Objects.equals(강연자, that.강연자) &&
                Objects.equals(강연장, that.강연장) &&
                Objects.equals(강연내용, that.강연내용) &&
                Objects.equals(강의시작시간, that.강의시작시간) &&
                강의시간 == that.강의시간 &&
                총인원 == that.총인원 &&
                신청인원 == that.신청인원;
    }

    @Override
    public int hashCode() {
        return Objects.hash(강의ID, 강연자, 강연장, 강연내용, 강의시작시간, 강의시간, 총인원, 신청인원);
    }
}
