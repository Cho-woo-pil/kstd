package com.homework.kstd.controller.venue;

import com.homework.kstd.service.venue.GetVenueService;
import com.homework.kstd.service.venue.InsertVenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kstd/venue")
public class VenueController {

    private final GetVenueService getVenueService;
    private final InsertVenueService insertVenueService;
    @GetMapping()
    @Operation(summary = "강의실 등록 API", description = "강의실 등록 성공 API 입니다.")
    @ApiResponse(responseCode = "200", description = "강의실 등록 성공")
    public ResponseEntity<?> insertVenue() {
        return getVenueService.getVenue();
    }

    @PostMapping()
    @Operation(summary = "강의실 조회 API", description = "강의실을 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "강의실 조회 성공")
    public ResponseEntity<?> insertVenue(@RequestParam(name = "venueName") String venueName) {

        return insertVenueService.insertVenue(venueName);
    }
}
