package com.homework.kstd.controller.venue;

import com.homework.kstd.service.venue.GetVenueService;
import com.homework.kstd.service.venue.InsertVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/venue")
public class GetVenueController {

    private final GetVenueService getVenueService;
    @GetMapping()
    public ResponseEntity<?> insertVenue() {
        return getVenueService.getVenue();
    }
}