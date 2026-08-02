package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ImportantStatusDTO;
import com.a08r.pick_location.models.important.ImportantStatusEntity;

public class ImportantStatusMapperImpl implements IImportantStatusMapper {

    @Override
    public ImportantStatusEntity importantStatusDTOToImportantStatusEntity(ImportantStatusDTO importantStatusDTO) {
        if (importantStatusDTO == null) {
            throw new NullPointerException("importantStatusDTO should not be null");
        }
        ImportantStatusEntity importantStatusEntity = new ImportantStatusEntity();
        importantStatusEntity.setId(importantStatusDTO.getId());
        importantStatusEntity.setImportantName(importantStatusDTO.getImportantName());
        return importantStatusEntity;
    }

    @Override
    public ImportantStatusDTO importantStatusEntityToImportantStatusDTO(ImportantStatusEntity importantStatusEntity) {
        if (importantStatusEntity == null) {
            throw new NullPointerException("importantStatusEntity should not be null");
        }
        ImportantStatusDTO importantStatusDTO = new ImportantStatusDTO();
        importantStatusDTO.setId(importantStatusEntity.getId());
        importantStatusDTO.setImportantName(importantStatusEntity.getImportantName());
        return importantStatusDTO;
    }
}
