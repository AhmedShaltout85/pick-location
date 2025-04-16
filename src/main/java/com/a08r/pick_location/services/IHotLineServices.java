package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.HotLineDTO;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHotLineServices {

    ResponseEntity<List<HotLineDTO>> getHotLineAll();

    ResponseEntity<HotLineDTO> createHotLineAddress(HotLineDTO newHotLineDTO);
    ResponseEntity<HotLineDTO> findHotLineByAddress(String address);
    ResponseEntity<HotLineDTO> updateHotLineByAddress(String address, HotLineDTO HotLineDTO);
    ResponseEntity<HotLineDTO> findById(Long id);


}
