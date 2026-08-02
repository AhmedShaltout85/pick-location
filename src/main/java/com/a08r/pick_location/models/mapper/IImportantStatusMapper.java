package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.ImportantStatusDTO;
import com.a08r.pick_location.models.important.ImportantStatusEntity;

public interface IImportantStatusMapper {
    ImportantStatusEntity importantStatusDTOToImportantStatusEntity(ImportantStatusDTO importantStatusDTO);
    ImportantStatusDTO importantStatusEntityToImportantStatusDTO(ImportantStatusEntity importantStatusEntity);
}
