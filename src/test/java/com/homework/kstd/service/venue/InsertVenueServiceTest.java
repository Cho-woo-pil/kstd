package com.homework.kstd.service.venue;

import com.homework.kstd.entity.Venue;
import com.homework.kstd.provider.VenueTestDataProvider;
import com.homework.kstd.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InsertVenueServiceTest {

    @Test
    void insertVenue() {
        // Mock 객체 생성
        VenueRepository venueRepository = Mockito.mock(VenueRepository.class);

        // 테스트용 Venue 객체 생성
        Venue testVenue = VenueTestDataProvider.createVenueWithId("Test Venue");

        // venueRepository.save() 메소드가 호출될 때 테스트용 Venue 객체 반환
        when(venueRepository.save(any())).thenReturn(testVenue);

        // 테스트할 서비스 생성
        InsertVenueService insertVenueService = new InsertVenueService(venueRepository);

        // 서비스 실행
        ResponseEntity<?> responseEntity = insertVenueService.insertVenue("Test Venue");

        // 결과 확인
        Venue responseVenue = (Venue) responseEntity.getBody();
        assertEquals("Test Venue", responseVenue.getVenueName());

        // venueRepository.save() 메소드가 1번 호출되었는지 확인
        verify(venueRepository, times(1)).save(any());
    }
}