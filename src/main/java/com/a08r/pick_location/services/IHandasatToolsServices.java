package com.a08r.pick_location.services;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHandasatToolsServices {

    ResponseEntity<List<HandasatToolsDTO>> getAllHandasatTools();
    ResponseEntity<List<String>> getAllHandasatToolsByHandasahName(String handasahName);

    ResponseEntity<HandasatToolsDTO> createHandasatTools(HandasatToolsDTO newHandasatToolsDTO);
}
