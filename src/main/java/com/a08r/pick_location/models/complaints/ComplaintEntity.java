package com.a08r.pick_location.models.complaints;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "complaints")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComplaintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Long complaintId;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "repeat_complaint_number")
    private Long repeatComplaintNumber;
    @Column(name = "complaint_source")
    private String complaintSource;
    @Column(name = "reporter_name")
    private String reporterName;
    @Column(name = "reporter_phone")
    private String reporterPhone;
    @Column(name = "complaint_address")
    private String complaintAddress;
    @Column(name = "complaint_repair_status")
    private String complaintRepairStatus;
    @Column(name = "approval_authority")
    private String approvalAuthority;
    @Column(name = "pump_diameter")
    private String pumpDiameter;
    @Column(name = "serious_status")
    private String seriousStatus;
    @Column(name = "complaint_status")
    private String complaintStatus;
    @Column(name = "complaint_note")
    private String complaintNote;
    @Column(name = "recipient_destination")
    private String recipientDestination;
    @Column(name = "recipient_user")
    private String recipientUser;
    @Column(name = "report_number")
    private Long reportNumber;
    @Column(name = "recipient_name")
    private String recipientName;
    @Column(name = "gis_link")
    private String gisLink;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "is_deleted")
    private int isDeleted;
    @Column(name = "is_finished")
    private int isFinished;
    @Column(name = "is_tracked")
    private int isTracked;
    @Column(name = "current_username")
    private String currentUsername;
    @Column(name = "deleted_at")
    private String deletedAt;
    @Column(name = "finished_at")
    private String finishedAt;
    @Column(name = "complaint_type")
    private String complaintType;
    @Column(name = "sector_name")
    private String sectorName;
    @Column(name = "urgency_number")
    private Long urgencyNumber;
}
