package com.homework.kstd.controller.lecture;

import com.homework.kstd.dto.RegistorLectureDto;
import com.homework.kstd.service.lecture.GetLectureApplicationListService;
import com.homework.kstd.service.lecture.GetLectureListService;
import com.homework.kstd.service.lecture.RegistorLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kstd/lecture")
public class LectureController {
    private final RegistorLectureService registorLectureService;
    private final GetLectureListService getLectureListService;
    private final GetLectureApplicationListService getLectureApplicationListService;

    @PostMapping("/registor")
    public ResponseEntity<?> registorLecture(@RequestBody RegistorLectureDto dto) {

        return registorLectureService.registorLecture(dto);
    }
    @GetMapping("/list/upcomming")
    public ResponseEntity<?> getUpcommingLectureList() {

        return getLectureListService.getUpcommingLectureList();
    }

    @GetMapping("/list/all")
    public ResponseEntity<?> getAllLectureList() {

        return getLectureListService.getAllLectureList();
    }

    @GetMapping("/list/popular")
    public ResponseEntity<?> getPopularLectureList() {

        return getLectureListService.getPopularLectureList();
    }

    @GetMapping("/applications/all")
    public ResponseEntity<?> getLectureApplicationList() {

        return getLectureApplicationListService.getLectureApplicationList();
    }

    @GetMapping("/applications/{lectureId}")
    public ResponseEntity<?> getLectureApplication(@PathVariable String lectureId) {

        return getLectureApplicationListService.getLectureApplication(lectureId);
    }



}
