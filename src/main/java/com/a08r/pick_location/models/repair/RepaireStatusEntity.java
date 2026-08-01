package com.a08r.pick_location.models.repair;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "repaire_status")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepaireStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "repaire_type")
    private String repaireType;
}
