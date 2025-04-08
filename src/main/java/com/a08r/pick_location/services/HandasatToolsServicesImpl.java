package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.HandasatToolsDTO;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.handasatTools.HandasatToolsEntity;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;
import com.a08r.pick_location.models.mapper.HandasatToolsMapperImpl;
import com.a08r.pick_location.models.mapper.HotLineDataMapperImpl;
import com.a08r.pick_location.models.mapper.IHandasatToolsMapper;
import com.a08r.pick_location.models.mapper.IHotLineDataMapper;
import com.a08r.pick_location.repositores.IHandasatToolsRepository;
import com.a08r.pick_location.repositores.IHotLineDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HandasatToolsServicesImpl implements IHandasatToolsServices {

    private final IHandasatToolsRepository iHandasatToolsRepository;
    private static final IHandasatToolsMapper I_HANDASAT_TOOLS_MAPPER = new HandasatToolsMapperImpl();

    @Autowired
    public HandasatToolsServicesImpl(IHandasatToolsRepository iHandasatToolsRepository) {
        this.iHandasatToolsRepository = iHandasatToolsRepository;
    }

    @Override
    public ResponseEntity<List<HandasatToolsDTO>> getAllHandasatTools() {
        List<HandasatToolsEntity> handasatToolsEntities = iHandasatToolsRepository.findAll();
        List<HandasatToolsDTO> handasatToolsDTOS = handasatToolsEntities
                .stream()
                .map(I_HANDASAT_TOOLS_MAPPER::handasatToolsEntityToHandasatToolsDTO)
                .collect(Collectors.toList());
        if (handasatToolsDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(handasatToolsDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HandasatToolsDTO> createHandasatTools(HandasatToolsDTO newHandasatToolsDTO) {
        final HandasatToolsEntity handasatToolsEntity = I_HANDASAT_TOOLS_MAPPER.handasatToolsDTOToHandasatToolsEntity(newHandasatToolsDTO);
        final HandasatToolsEntity createHandasatToolsEntity = this.iHandasatToolsRepository.save(handasatToolsEntity);
        HandasatToolsDTO handasatToolsDTO = I_HANDASAT_TOOLS_MAPPER.handasatToolsEntityToHandasatToolsDTO(createHandasatToolsEntity);
        return new ResponseEntity<>(handasatToolsDTO, HttpStatus.CREATED);

    }


}
