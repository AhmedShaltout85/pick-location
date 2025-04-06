package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.dto.PickLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHotLineDataServices {

    ResponseEntity<List<HotLineDataDTO>> getHotLineAllData();

    ResponseEntity<HotLineDataDTO> createHotLineAddress(HotLineDataDTO newHotLineDataDTO);
}
