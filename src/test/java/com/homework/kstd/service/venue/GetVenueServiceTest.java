package com.homework.kstd.service.venue;

import com.homework.kstd.entity.Venue;
import com.homework.kstd.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetVenueServiceTest {

    @Test
    void getVenue() {
        // Mock 객체 생성
        VenueRepository venueRepository = Mockito.mock(VenueRepository.class);

        // 테스트용 Venue 객체 리스트 생성
        List<Venue> testVenueList = Arrays.asList(
                new Venue("Venue 1"),
                new Venue("Venue 2"),
                new Venue("Venue 3")
        );

        // venueRepository.findAll() 메소드가 호출될 때 테스트용 Venue 객체 리스트 반환
        when(venueRepository.findAll()).thenReturn(testVenueList);

        // 테스트할 서비스 생성
        GetVenueService getVenueService = new GetVenueService(venueRepository);

        // 서비스 실행
        ResponseEntity<?> responseEntity = getVenueService.getVenue();

        // 결과 확인
        List<Venue> responseVenueList = (List<Venue>) responseEntity.getBody();
        assertEquals(testVenueList, responseVenueList);

        // venueRepository.findAll() 메소드가 1번 호출되었는지 확인
        verify(venueRepository, times(1)).findAll();
    }
}