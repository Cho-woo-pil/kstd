package com.homework.kstd.controller.member;

import com.homework.kstd.dto.ApplyLectureDto;
import com.homework.kstd.service.member.ApplyLectureService;
import com.homework.kstd.service.member.CancleLectureService;
import com.homework.kstd.service.member.GetApplyHistoryService;
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
    public ResponseEntity<?> registorLecture(@RequestBody ApplyLectureDto dto) {

        return applyLectureService.applyLecture(dto);
    }

    @PatchMapping("/cancle")
    public ResponseEntity<?> cancelLectureApplication(@RequestBody ApplyLectureDto dto) {
        return cancleLectureService.cancelLecture(dto);
    }

    @GetMapping("/apply/history/{employeeId}")
    public ResponseEntity<?> getApplyHistory(@PathVariable String employeeId) {
        return getApplyHistoryService.getApplyHistory(employeeId);
    }
}
