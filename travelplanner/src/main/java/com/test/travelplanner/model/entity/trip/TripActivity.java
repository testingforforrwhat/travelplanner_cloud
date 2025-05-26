// TripActivity.java
package com.test.travelplanner.model.entity.trip;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trip_activities")
@Data
public class TripActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "activity_time")
    private String time;
    
    private String title;
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "itinerary_id")
    private TripItinerary itinerary;
}