package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ComplaintDTO;
import com.a08r.pick_location.models.complaints.ComplaintEntity;

public interface IComplaintMapper {
    ComplaintEntity complaintDTOToComplaintEntity(ComplaintDTO complaintDTO);
    ComplaintDTO complaintEntityToComplaintDTO(ComplaintEntity complaintEntity);
}
