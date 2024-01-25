package com.homework.kstd.provider;

import com.homework.kstd.entity.Venue;

import java.util.UUID;

public class VenueTestDataProvider {

    public static Venue createVenueWithId(String venueName) {
        Venue venue = new Venue(venueName);
        return venue;
    }
}