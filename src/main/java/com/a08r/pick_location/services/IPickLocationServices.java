package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPickLocationServices {

    ResponseEntity<List<PickLocationDTO>> getAddress();

    ResponseEntity<List<PickLocationDTO>> getPickLocationByFlag(int flag);
    ResponseEntity<List<PickLocationDTO>> getPickLocationByFlagAndIsFinished(int flag, int is_finished);

    ResponseEntity<PickLocationDTO> createAddress(PickLocationDTO newPickLocationDTO);

    ResponseEntity<PickLocationDTO> updateAddress(Long id, PickLocationDTO newPickLocationDTO);

    ResponseEntity<PickLocationDTO> updatePickLocationByAddress(String address, PickLocationDTO newPickLocationDTO);
    ResponseEntity<PickLocationDTO> updatePickLocationHandasah(String address, PickLocationDTO newPickLocationDTO);
    ResponseEntity<PickLocationDTO> updatePickLocationTechnicalUser(String address, PickLocationDTO newPickLocationDTO);
    ResponseEntity<PickLocationDTO> updatePickLocationIsFinished(String address, PickLocationDTO newPickLocationDTO);
    ResponseEntity<PickLocationDTO> findPickLocationByAddressAndFlag(String address, int flag);
    ResponseEntity<PickLocationDTO> findPickLocationByAddress(String address);

}
