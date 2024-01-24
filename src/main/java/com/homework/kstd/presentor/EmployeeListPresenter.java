package com.homework.kstd.presentor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EmployeeListPresenter {
    private String 사원번호;
    private LocalDateTime 신청시간;

    public EmployeeListPresenter(String 사원번호, LocalDateTime 신청시간) {
        this.사원번호 = 사원번호;
        this.신청시간 = 신청시간;
    }
}

