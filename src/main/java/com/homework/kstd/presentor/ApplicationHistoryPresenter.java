package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class ApplicationHistoryPresenter {
    private String 강연ID;
    private LocalDateTime 신청일;

    public ApplicationHistoryPresenter(String 강연ID, LocalDateTime 신청일) {
        this.강연ID = 강연ID;
        this.신청일 = 신청일;
    }


    // 테스트코드 해시 비교를 위한 메서드 정의
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationHistoryPresenter that = (ApplicationHistoryPresenter) o;
        return Objects.equals(강연ID, that.강연ID) &&
                Objects.equals(신청일, that.신청일);
    }

    @Override
    public int hashCode() {
        return Objects.hash(강연ID, 신청일);
    }
}
