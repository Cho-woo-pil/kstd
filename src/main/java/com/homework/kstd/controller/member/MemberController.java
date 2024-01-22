package com.homework.kstd.controller.member;

import com.homework.kstd.dto.ApplyLectureDto;
import com.homework.kstd.dto.RegistorLectureDto;
import com.homework.kstd.service.member.ApplyLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kstd/lecture")
public class MemberController {

    private final ApplyLectureService applyLectureService;
    @PostMapping("/apply")
    public ResponseEntity<?> registorLecture(@RequestBody ApplyLectureDto dto) {

        return applyLectureService.applyLecture(dto);
    }
}
