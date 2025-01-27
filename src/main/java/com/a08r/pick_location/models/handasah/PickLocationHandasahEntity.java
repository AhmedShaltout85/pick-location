package com.a08r.pick_location.models.handasah;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pick_location_handasah")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PickLocationHandasahEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "handasah_name")
    private String handasahName;
    @Column(name = "store_name")
    private String storeName;
    @Column(name = "store_number")
    private int storeNumber;
}
