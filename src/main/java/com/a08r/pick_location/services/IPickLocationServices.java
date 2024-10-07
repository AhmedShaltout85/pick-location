package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPickLocationServices {

    ResponseEntity<List<PickLocationDTO>> getAddress();

    ResponseEntity<List<PickLocationDTO>> getPickLocationByFlag(int flag);

//    ResponseEntity<PickLocationDTO> getAddress();

    ResponseEntity<PickLocationDTO> updateAddress(Long id, PickLocationDTO newPickLocationDTO);

}
