package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ComplaintTypeDTO;
import com.a08r.pick_location.models.complaint.ComplaintTypeEntity;

public class ComplaintTypeMapperImpl implements IComplaintTypeMapper {

    @Override
    public ComplaintTypeEntity complaintTypeDTOToComplaintTypeEntity(ComplaintTypeDTO complaintTypeDTO) {
        if (complaintTypeDTO == null) {
            throw new NullPointerException("complaintTypeDTO should not be null");
        }
        ComplaintTypeEntity complaintTypeEntity = new ComplaintTypeEntity();
        complaintTypeEntity.setId(complaintTypeDTO.getId());
        complaintTypeEntity.setComplaintName(complaintTypeDTO.getComplaintName());
        return complaintTypeEntity;
    }

    @Override
    public ComplaintTypeDTO complaintTypeEntityToComplaintTypeDTO(ComplaintTypeEntity complaintTypeEntity) {
        if (complaintTypeEntity == null) {
            throw new NullPointerException("complaintTypeEntity should not be null");
        }
        ComplaintTypeDTO complaintTypeDTO = new ComplaintTypeDTO();
        complaintTypeDTO.setId(complaintTypeEntity.getId());
        complaintTypeDTO.setComplaintName(complaintTypeEntity.getComplaintName());
        return complaintTypeDTO;
    }
}
