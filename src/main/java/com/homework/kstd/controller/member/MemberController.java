package com.homework.kstd.controller.member;

import com.homework.kstd.dto.ApplyLectureDto;
import com.homework.kstd.service.member.ApplyLectureService;
import com.homework.kstd.service.member.CancleLectureService;
import com.homework.kstd.service.member.GetApplyHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kstd/lecture")
public class MemberController {

    private final ApplyLectureService applyLectureService;
    private final CancleLectureService cancleLectureService;
    private final GetApplyHistoryService getApplyHistoryService;
    @PostMapping("/apply")
    @Operation(summary = "강연 신청 API", description = "강연을 신청하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "강연신청 성공")
    public ResponseEntity<?> applyLecture(@RequestBody ApplyLectureDto dto) {

        return applyLectureService.applyLecture(dto);
    }

    @PatchMapping("/cancle")
    @Operation(summary = "강연 취소 API", description = "강연을 취소하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "강연취소 성공")
    public ResponseEntity<?> cancelLectureApplication(@RequestBody ApplyLectureDto dto) {
        return cancleLectureService.cancelLecture(dto);
    }

    @GetMapping("/apply/history/{employeeId}")
    @Operation(summary = "강연 신청 내역 조회 API", description = "회원이 신청한 내역을 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "강연 내역 조회 성공")
    public ResponseEntity<?> getApplyHistory(@PathVariable String employeeId) {
        return getApplyHistoryService.getApplyHistory(employeeId);
    }
}
