package com.a08r.pick_location.models.sector;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sectors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "sector_name")
    private String sectorName;
}
