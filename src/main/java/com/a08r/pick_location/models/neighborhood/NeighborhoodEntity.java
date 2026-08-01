package com.a08r.pick_location.models.neighborhood;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "neighborhoods")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NeighborhoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "neighborhood_name")
    private String neighborhoodName;
}
