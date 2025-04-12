package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.HotLineDTO;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHotLineServices {

    ResponseEntity<List<HotLineDTO>> getHotLineAll();

    ResponseEntity<HotLineDTO> createHotLineAddress(HotLineDTO newHotLineDTO);
}
