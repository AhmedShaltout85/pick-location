package com.a08r.pick_location.models.location;



import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "Locations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PickLocationEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Address")
    private String address;
    @Column(name = "Latitude")
    private String latitude;
    @Column(name = "Longtiude")
    private String longitude;
    @Column(name = "Flag")
    private int flag;
    @Column(name = "Gis_Url")
    private String gis_url;

}
