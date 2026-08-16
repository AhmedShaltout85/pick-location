package com.a08r.pick_location.models.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {
    private Long complaintId;
    private String createdAt;
    private String updatedAt;
    private String neighborhood;
    private Long repeatComplaintNumber;
    private String complaintSource;
    private String reporterName;
    private String reporterPhone;
    private String complaintAddress;
    private String complaintRepairStatus;
    private String approvalAuthority;
    private String pumpDiameter;
    private String seriousStatus;
    private String complaintStatus;
    private String complaintNote;
    private String recipientDestination;
    private String recipientUser;
    private Long reportNumber;
    private String recipientName;
    private String gisLink;
    private String longitude;
    private String latitude;
    private int isDeleted;
    private int isFinished;
    private int isTracked;
    private String currentUsername;
    private String deletedAt;
    private String finishedAt;
    private String complaintType;
    private String sectorName;
    private Long urgencyNumber;
}
