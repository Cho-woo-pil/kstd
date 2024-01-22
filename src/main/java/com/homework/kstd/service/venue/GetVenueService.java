package com.homework.kstd.service.venue;

import com.homework.kstd.entity.Venue;
import com.homework.kstd.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetVenueService {
    private final VenueRepository venueRepository;
    public ResponseEntity<?> getVenue(){
        List<Venue> venueList = venueRepository.findAll();
        return ResponseEntity.ok(venueList);
    }
}
