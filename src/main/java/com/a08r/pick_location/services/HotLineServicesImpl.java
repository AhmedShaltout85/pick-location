package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.HotLineDTO;
import com.a08r.pick_location.models.dto.HotLineDataDTO;
import com.a08r.pick_location.models.hotline.HotLineDataEntity;
import com.a08r.pick_location.models.hotline.HotLineEntity;
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
}
