package com.homework.kstd.service.venue;

import com.homework.kstd.entity.Venue;
import com.homework.kstd.repository.VenueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsertVenueService {
    private final VenueRepository venueRepository;

    @Transactional
    public ResponseEntity<?> insertVenue(String venueName){
        Venue venue = new Venue(venueName);
        venueRepository.save(venue);
        return ResponseEntity.ok(venue);
    }
}
