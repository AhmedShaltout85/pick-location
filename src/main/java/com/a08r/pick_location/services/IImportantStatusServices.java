package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.ImportantStatusDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IImportantStatusServices {
    ResponseEntity<List<ImportantStatusDTO>> getAll();
    ResponseEntity<ImportantStatusDTO> findById(Long id);
    ResponseEntity<ImportantStatusDTO> create(ImportantStatusDTO importantStatusDTO);
    ResponseEntity<ImportantStatusDTO> update(Long id, ImportantStatusDTO importantStatusDTO);
    ResponseEntity<String> remove(Long id);
}
