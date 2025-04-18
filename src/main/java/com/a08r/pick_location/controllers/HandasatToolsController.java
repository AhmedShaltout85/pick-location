package com.a08r.pick_location.controllers;

import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.services.IHandasatToolsServices;
import com.a08r.pick_location.services.IHotLineDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/handasat-tools")
@CrossOrigin(origins ="*" )  // Allow CORS for Flutter Web
//TODO://http://localhost:9999/pick-location/api/v1/handasat-tools
//TODO: we use @CrossOrigin to enable CORS for this controller (this mean no need to using https://localhost:9999)
//TODO: https://documenter.getpostman.com/view/11963423/2sAXxQcrcV => POSTMAN DOCUMENTATION
public class HandasatToolsController {
    private final IHandasatToolsServices iHandasatToolsServices;

    @Autowired
    public HandasatToolsController(IHandasatToolsServices iHandasatToolsServices) {
        this.iHandasatToolsServices = iHandasatToolsServices;
    }


    //TODO://GET http://localhost:9999/pick-location/api/v1/handasat-tools/all
    @GetMapping("/all")
    public ResponseEntity<List<HandasatToolsDTO>> getAllHandasatTools() {
        return this.iHandasatToolsServices.getAllHandasatTools();
    }
  //TODO://GET http://localhost:9999/pick-location/api/v1/handasat-tools/all/{handasahName}
    @GetMapping("/all/{handasahName}")
    public ResponseEntity<List<String>> getAllHandasatToolsByHandasahName(@PathVariable String handasahName) {
        return this.iHandasatToolsServices.getAllHandasatToolsByHandasahName(handasahName);
    }



    //TODO://POST http://localhost:9999/pick-location/api/v1/handasat-tools/create-tool
    @PostMapping("/create-tool")
    public ResponseEntity<HandasatToolsDTO> createHandasatTool(@RequestBody HandasatToolsDTO addHandasatToolsDto) {
        return this.iHandasatToolsServices.createHandasatTools(addHandasatToolsDto);
    }


}
