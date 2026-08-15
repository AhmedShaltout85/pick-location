package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ComplaintDTO;
import com.a08r.pick_location.models.complaints.ComplaintEntity;

public class ComplaintMapperImpl implements IComplaintMapper {

    private static final String DEFAULT_STRING = "\u0644\u0645 \u064a\u062f\u0631\u062c";

    private String defaultIfBlank(String value) {
        return (value == null || value.isBlank()) ? DEFAULT_STRING : value;
    }

    @Override
    public ComplaintEntity complaintDTOToComplaintEntity(ComplaintDTO complaintDTO) {
        if (complaintDTO == null) {
            throw new NullPointerException("complaintDTO should not be null");
        }
        ComplaintEntity complaintEntity = new ComplaintEntity();
        complaintEntity.setComplaintId(complaintDTO.getComplaintId());

        String now = java.time.LocalDateTime.now().toString();
        complaintEntity.setCreatedAt(
                (complaintDTO.getCreatedAt() == null || complaintDTO.getCreatedAt().isBlank()) ? now : complaintDTO.getCreatedAt());
        complaintEntity.setUpdatedAt(
                (complaintDTO.getUpdatedAt() == null || complaintDTO.getUpdatedAt().isBlank()) ? now : complaintDTO.getUpdatedAt());

        complaintEntity.setNeighborhood(defaultIfBlank(complaintDTO.getNeighborhood()));
        complaintEntity.setRepeatComplaintNumber(complaintDTO.getRepeatComplaintNumber() != null ? complaintDTO.getRepeatComplaintNumber() : 0L);
        complaintEntity.setComplaintSource(defaultIfBlank(complaintDTO.getComplaintSource()));
        complaintEntity.setReporterName(defaultIfBlank(complaintDTO.getReporterName()));
        complaintEntity.setReporterPhone(defaultIfBlank(complaintDTO.getReporterPhone()));
        complaintEntity.setComplaintAddress(complaintDTO.getComplaintAddress());
        complaintEntity.setComplaintRepairStatus(defaultIfBlank(complaintDTO.getComplaintRepairStatus()));
        complaintEntity.setApprovalAuthority(defaultIfBlank(complaintDTO.getApprovalAuthority()));
        complaintEntity.setPumpDiameter(defaultIfBlank(complaintDTO.getPumpDiameter()));
        complaintEntity.setSeriousStatus(defaultIfBlank(complaintDTO.getSeriousStatus()));
        complaintEntity.setComplaintStatus(defaultIfBlank(complaintDTO.getComplaintStatus()));
        complaintEntity.setComplaintNote(defaultIfBlank(complaintDTO.getComplaintNote()));
        complaintEntity.setRecipientDestination(defaultIfBlank(complaintDTO.getRecipientDestination()));
        complaintEntity.setRecipientUser(defaultIfBlank(complaintDTO.getRecipientUser()));
        complaintEntity.setReportNumber(complaintDTO.getReportNumber() != null ? complaintDTO.getReportNumber() : 0L);
        complaintEntity.setRecipientName(defaultIfBlank(complaintDTO.getRecipientName()));
        complaintEntity.setGisLink(defaultIfBlank(complaintDTO.getGisLink()));
        complaintEntity.setLongitude(defaultIfBlank(complaintDTO.getLongitude()));
        complaintEntity.setLatitude(defaultIfBlank(complaintDTO.getLatitude()));
        complaintEntity.setIsDeleted(complaintDTO.getIsDeleted());
        complaintEntity.setIsFinished(complaintDTO.getIsFinished());
        complaintEntity.setIsTracked(complaintDTO.getIsTracked());
        complaintEntity.setCurrentUsername(complaintDTO.getCurrentUsername());
        complaintEntity.setDeletedAt(complaintDTO.getDeletedAt());
        complaintEntity.setFinishedAt(complaintDTO.getFinishedAt());
        complaintEntity.setComplaintType(defaultIfBlank(complaintDTO.getComplaintType()));
        complaintEntity.setSectorName(defaultIfBlank(complaintDTO.getSectorName()));
        return complaintEntity;
    }

    @Override
    public ComplaintDTO complaintEntityToComplaintDTO(ComplaintEntity complaintEntity) {
        if (complaintEntity == null) {
            throw new NullPointerException("complaintEntity should not be null");
        }
        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setComplaintId(complaintEntity.getComplaintId());
        complaintDTO.setCreatedAt(complaintEntity.getCreatedAt());
        complaintDTO.setUpdatedAt(complaintEntity.getUpdatedAt());
        complaintDTO.setNeighborhood(complaintEntity.getNeighborhood());
        complaintDTO.setRepeatComplaintNumber(complaintEntity.getRepeatComplaintNumber());
        complaintDTO.setComplaintSource(complaintEntity.getComplaintSource());
        complaintDTO.setReporterName(complaintEntity.getReporterName());
        complaintDTO.setReporterPhone(complaintEntity.getReporterPhone());
        complaintDTO.setComplaintAddress(complaintEntity.getComplaintAddress());
        complaintDTO.setComplaintRepairStatus(complaintEntity.getComplaintRepairStatus());
        complaintDTO.setApprovalAuthority(complaintEntity.getApprovalAuthority());
        complaintDTO.setPumpDiameter(complaintEntity.getPumpDiameter());
        complaintDTO.setSeriousStatus(complaintEntity.getSeriousStatus());
        complaintDTO.setComplaintStatus(complaintEntity.getComplaintStatus());
        complaintDTO.setComplaintNote(complaintEntity.getComplaintNote());
        complaintDTO.setRecipientDestination(complaintEntity.getRecipientDestination());
        complaintDTO.setRecipientUser(complaintEntity.getRecipientUser());
        complaintDTO.setReportNumber(complaintEntity.getReportNumber());
        complaintDTO.setRecipientName(complaintEntity.getRecipientName());
        complaintDTO.setGisLink(complaintEntity.getGisLink());
        complaintDTO.setLongitude(complaintEntity.getLongitude());
        complaintDTO.setLatitude(complaintEntity.getLatitude());
        complaintDTO.setIsDeleted(complaintEntity.getIsDeleted());
        complaintDTO.setIsFinished(complaintEntity.getIsFinished());
        complaintDTO.setIsTracked(complaintEntity.getIsTracked());
        complaintDTO.setCurrentUsername(complaintEntity.getCurrentUsername());
        complaintDTO.setDeletedAt(complaintEntity.getDeletedAt());
        complaintDTO.setFinishedAt(complaintEntity.getFinishedAt());
        complaintDTO.setComplaintType(complaintEntity.getComplaintType());
        complaintDTO.setSectorName(complaintEntity.getSectorName());
        return complaintDTO;
    }
}
