package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApplicationHistoryPresenter {
    private String 강연ID;
    private LocalDateTime 신청일;

    public ApplicationHistoryPresenter(String 강연ID, LocalDateTime 신청일) {
        this.강연ID = 강연ID;
        this.신청일 = 신청일;
    }
}
