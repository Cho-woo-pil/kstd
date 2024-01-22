package com.homework.kstd.controller.lecture;

import com.homework.kstd.dto.RegistorLectureDto;
import com.homework.kstd.service.lecture.RegistorLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private final RegistorLectureService registorLectureService;

    @PostMapping("/registor")
    public ResponseEntity<?> registorLecture(@RequestBody RegistorLectureDto dto) {

        return registorLectureService.registorLecture(dto);
    }
}
