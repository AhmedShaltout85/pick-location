package com.a08r.pick_location.models.approval;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "approval_authorities")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApprovalAuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "approval_name")
    private String approvalName;
}
