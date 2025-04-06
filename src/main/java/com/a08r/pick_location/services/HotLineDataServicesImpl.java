package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;
import com.a08r.pick_location.models.mapper.HotLineDataMapperImpl;
import com.a08r.pick_location.models.mapper.IHotLineDataMapper;
import com.a08r.pick_location.repositores.IHotLineDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotLineDataServicesImpl implements IHotLineDataServices {

    private final IHotLineDataRepository iHotLineDataRepository;
    private static final IHotLineDataMapper I_HOT_LINE_DATA_MAPPER = new HotLineDataMapperImpl();

    @Autowired
    public HotLineDataServicesImpl(IHotLineDataRepository iHotLineDataRepository) {
        this.iHotLineDataRepository = iHotLineDataRepository;
    }

    @Override
    public ResponseEntity<List<HotLineDataDTO>> getHotLineAllData() {

        List<HotLineDataEntity> hotLineDataEntities = iHotLineDataRepository.findAll();
        List<HotLineDataDTO> hotLineDataDTOS = hotLineDataEntities
                .stream()
                .map(I_HOT_LINE_DATA_MAPPER::hotLineDataEntityToHotLineDataDTO)
                .collect(Collectors.toList());
        if (hotLineDataDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(hotLineDataDTOS, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<HotLineDataDTO> createHotLineAddress(HotLineDataDTO newHotLineDataDTO) {
        final HotLineDataEntity hotLineDataEntity = I_HOT_LINE_DATA_MAPPER.hotLineDataDTOToHotLineDataEntity(newHotLineDataDTO);
        final HotLineDataEntity createHotLineDataEntity = this.iHotLineDataRepository.save(hotLineDataEntity);
         HotLineDataDTO hotLineDataDTO = I_HOT_LINE_DATA_MAPPER.hotLineDataEntityToHotLineDataDTO(createHotLineDataEntity);
        return new ResponseEntity<>(hotLineDataDTO, HttpStatus.CREATED);

    }
}
