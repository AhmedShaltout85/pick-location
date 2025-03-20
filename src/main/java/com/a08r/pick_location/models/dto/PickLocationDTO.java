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
    private String handasah_name;
    private String technical_name;
    private int is_finished;
    private int is_approved;
    private String caller_name;
    private String caller_phone;
    private String broker_type;
    private int video_call;

}
