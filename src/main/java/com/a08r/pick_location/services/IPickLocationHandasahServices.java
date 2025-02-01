package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.PickLocationHandasahDTO;
import com.a08r.pick_location.models.dto.PickLocationUsersDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPickLocationHandasahServices {

    ResponseEntity<List<String>> getAllHandasah();
//    ResponseEntity<List<PickLocationHandasahDTO>> getAllHandasah();

    ResponseEntity<PickLocationHandasahDTO> findHandasahById(Long id);

    ResponseEntity<PickLocationHandasahDTO> createHandasah(PickLocationHandasahDTO newPickLocationHandasahDTO);



}
