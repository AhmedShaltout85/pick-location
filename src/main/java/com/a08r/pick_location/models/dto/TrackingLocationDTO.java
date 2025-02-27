package com.a08r.pick_location.models.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingLocationDTO {

    private Long id;
    private String address;
    private String latitude;
    private String longitude;
    private String technicalName;
    private String startLatitude;
    private String startLongitude;
    private String currentLatitude;
    private String currentLongitude;
}
