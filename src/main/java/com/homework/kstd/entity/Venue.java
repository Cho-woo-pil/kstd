package com.homework.kstd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Venue {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID venueId;

    private String venueName;

    public Venue(String venueName) {
        this.venueName = venueName;
    }

}
