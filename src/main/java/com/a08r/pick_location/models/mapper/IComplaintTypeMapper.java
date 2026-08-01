package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ComplaintTypeDTO;
import com.a08r.pick_location.models.complaint.ComplaintTypeEntity;

public interface IComplaintTypeMapper {
    ComplaintTypeEntity complaintTypeDTOToComplaintTypeEntity(ComplaintTypeDTO complaintTypeDTO);
    ComplaintTypeDTO complaintTypeEntityToComplaintTypeDTO(ComplaintTypeEntity complaintTypeEntity);
}
