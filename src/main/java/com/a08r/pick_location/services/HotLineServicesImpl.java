package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.HotLineDTO;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;
import com.a08r.pick_location.models.hotline.HotLineEntity;
import com.a08r.pick_location.models.location.PickLocationEntity;
import com.a08r.pick_location.models.mapper.HotLineDataMapperImpl;
import com.a08r.pick_location.models.mapper.HotLineMapperImpl;
import com.a08r.pick_location.models.mapper.IHotLineDataMapper;
import com.a08r.pick_location.models.mapper.IHotLineMapper;
import com.a08r.pick_location.repositores.IHotLineDataRepository;
import com.a08r.pick_location.repositores.IHotLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotLineServicesImpl implements IHotLineServices {

    private final IHotLineRepository iHotLineRepository;
    private static final IHotLineMapper I_HOT_LINE_MAPPER = new HotLineMapperImpl();

    @Autowired
    public HotLineServicesImpl(IHotLineRepository iHotLineRepository) {
        this.iHotLineRepository = iHotLineRepository;
    }

    @Override
    public ResponseEntity<List<HotLineDTO>> getHotLineAll() {

        List<HotLineEntity> hotLineEntities = iHotLineRepository.findAll();
        List<HotLineDTO> hotLineDTOS = hotLineEntities
                .stream()
                .map(I_HOT_LINE_MAPPER::hotLineEntityToHotLineDTO)
                .collect(Collectors.toList());
        if (hotLineDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(hotLineDTOS, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<HotLineDTO> createHotLineAddress(HotLineDTO newHotLineDTO) {
        final HotLineEntity hotLineEntity = I_HOT_LINE_MAPPER.hotLineDTOToHotLineEntity(newHotLineDTO);
        final HotLineEntity createHotLineEntity = this.iHotLineRepository.save(hotLineEntity);
         HotLineDTO hotLineDTO = I_HOT_LINE_MAPPER.hotLineEntityToHotLineDTO(createHotLineEntity);
        return new ResponseEntity<>(hotLineDTO, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<HotLineDTO> findHotLineByAddress(String address) {

        Optional<HotLineEntity> hotLineEntity = iHotLineRepository.findByAddress(address);
        if (hotLineEntity.isEmpty()) {
//            return new ResponseEntity<>(new PickLocationDTO(), HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        HotLineEntity exitingHotLineEntity = hotLineEntity.get();
        HotLineDTO hotLineDTO = I_HOT_LINE_MAPPER.hotLineEntityToHotLineDTO(exitingHotLineEntity);
        return new ResponseEntity<>(hotLineDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<HotLineDTO> updateHotLineByAddress(String address, HotLineDTO newHotLineDTO) {

        Optional<HotLineEntity> hotLineEntity = iHotLineRepository.findByAddress(address);
        if (hotLineEntity.isEmpty()) {
//            return new ResponseEntity<>(new PickLocationDTO(), HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("the item with address: " + address + " not found!...");
        }
        HotLineEntity exitingHotLineEntity = hotLineEntity.get();
        exitingHotLineEntity.setX(newHotLineDTO.getX());
        exitingHotLineEntity.setY(newHotLineDTO.getY());
        exitingHotLineEntity.setFinalClosed(newHotLineDTO.isFinalClosed());

        HotLineEntity updateHotLineEntity = this.iHotLineRepository.save(exitingHotLineEntity);
        HotLineDTO hotLineDTO = I_HOT_LINE_MAPPER.hotLineEntityToHotLineDTO(updateHotLineEntity);
        return new ResponseEntity<>(hotLineDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HotLineDTO> findById(Long id) {
        Optional<HotLineEntity> hotLineEntity = iHotLineRepository.findById(id);
        if(hotLineEntity.isEmpty()){
            throw new RecordNotFoundException("the item with id: "+id +" not found!...");

        }
        HotLineDTO hotLineDTO = I_HOT_LINE_MAPPER.hotLineEntityToHotLineDTO(hotLineEntity.get());
        return new ResponseEntity<>(hotLineDTO, HttpStatus.OK);

    }



}
