package com.a08r.pick_location.models.location;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tracking_locations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrackingLocationEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "technical_Name")
    private String technicalName;
    @Column(name = "start_latitude")
    private String startLatitude;
    @Column(name = "start_longitude")
    private String startLongitude;
    @Column(name = "current_latitude")
    private String currentLatitude;
    @Column(name = "current_longitude")
    private String currentLongitude;

}
