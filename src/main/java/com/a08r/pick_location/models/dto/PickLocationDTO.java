package com.a08r.pick_location.models.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickLocationDTO {

    private Long id;
    private String address;
    private String latitude;
    private String longitude;
    private int flag;
    private String gis_url;

}
