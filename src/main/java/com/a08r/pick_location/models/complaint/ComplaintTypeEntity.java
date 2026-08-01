package com.a08r.pick_location.models.complaint;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "complaint_type")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComplaintTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "complaint_name")
    private String complaintName;
}
