package com.homework.kstd.repository;

import com.homework.kstd.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    List<Venue> findAll();
}
