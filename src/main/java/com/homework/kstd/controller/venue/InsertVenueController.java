package com.homework.kstd.controller.venue;


import com.homework.kstd.service.venue.InsertVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/venue")
public class InsertVenueController {

    private final InsertVenueService insertVenueService;
    @PostMapping()
    public ResponseEntity<?> insertVenue(@RequestParam(name = "venueName") String venueName) {

        return insertVenueService.insertVenue(venueName);
    }
}
