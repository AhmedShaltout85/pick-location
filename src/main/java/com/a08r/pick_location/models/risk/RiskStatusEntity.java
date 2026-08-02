package com.a08r.pick_location.models.risk;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "risk_status")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RiskStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "risk_name")
    private String riskName;
}
