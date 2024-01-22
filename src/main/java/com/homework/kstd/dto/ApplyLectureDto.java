package com.homework.kstd.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class ApplyLectureDto {
    private String lectureId;

    @Length(min = 5, max = 5, message = "사원번호는 5글자여야 합니다.")
    private String employeeId;
}
