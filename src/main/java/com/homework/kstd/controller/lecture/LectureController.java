package com.homework.kstd.controller.lecture;

import com.homework.kstd.dto.RegistorLectureDto;
import com.homework.kstd.service.lecture.GetLectureApplicationListService;
import com.homework.kstd.service.lecture.GetLectureListService;
import com.homework.kstd.service.lecture.RegistorLectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kstd/lecture")
@Tag(name = "강연 API", description = "강연관련 API")
public class LectureController {
    private final RegistorLectureService registorLectureService;
    private final GetLectureListService getLectureListService;
    private final GetLectureApplicationListService getLectureApplicationListService;

    @PostMapping("/registor")
    @Operation(summary = "강연등록 API", description = "강연을 등록하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "강연 등록 성공")
    public ResponseEntity<?> registorLecture(@RequestBody RegistorLectureDto dto) {

        return registorLectureService.registorLecture(dto);
    }
    @GetMapping("/list/upcomming")
    @Operation(summary = "다가오는 강연 조회 API", description = "다가오는 강연을 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "강연 조회 성공")
    public ResponseEntity<?> getUpcommingLectureList() {

        return getLectureListService.getUpcommingLectureList();
    }

    @GetMapping("/list/all")
    @Operation(summary = "전체 강연 조회 API", description = "전체 강연을 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "전체 강연 조회 성공")
    public ResponseEntity<?> getAllLectureList() {

        return getLectureListService.getAllLectureList();
    }

    @GetMapping("/list/popular")
    @Operation(summary = "인기 강연 조회 API", description = "인기 강연을 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "인기 강연 조회 성공")
    public ResponseEntity<?> getPopularLectureList() {

        return getLectureListService.getPopularLectureList();
    }

    @GetMapping("/applications/all")
    @Operation(summary = "강연 신청자 조회 API", description = "강연 모두와 신청자 모두를 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "신청자 조회 성공")
    public ResponseEntity<?> getLectureApplicationList() {

        return getLectureApplicationListService.getLectureApplicationList();
    }

    @GetMapping("/applications/{lectureId}")
    @Operation(summary = "특정 강연 신청자 조회 API", description = "특정강연의 신청자 모두를 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "신청자 조회 성공")
    public ResponseEntity<?> getLectureApplication(@PathVariable String lectureId) {

        return getLectureApplicationListService.getLectureApplication(lectureId);
    }



}
