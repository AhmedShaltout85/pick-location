package com.a08r.pick_location.services;

import com.a08r.pick_location.errors.RecordNotFoundException;
import com.a08r.pick_location.models.dto.UsersRequestsToolsDTO;
import com.a08r.pick_location.models.handasatTools.UsersRequestsToolsEntity;
import com.a08r.pick_location.models.mapper.IUsersRequestsToolsMapper;
import com.a08r.pick_location.models.mapper.UsersRequestsToolsMapperImpl;
import com.a08r.pick_location.repositores.IUsersRequestsToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersRequestsToolsServicesImpl implements IUsersRequestsToolsServices {


    private final IUsersRequestsToolsRepository iUsersRequestsToolsRepository;

    private static final IUsersRequestsToolsMapper I_USERS_REQUESTS_TOOLS_MAPPER = new UsersRequestsToolsMapperImpl();

    @Autowired
    public UsersRequestsToolsServicesImpl(IUsersRequestsToolsRepository iUsersRequestsToolsRepository) {
        this.iUsersRequestsToolsRepository = iUsersRequestsToolsRepository;
    }

    @Override
    public ResponseEntity<List<UsersRequestsToolsDTO>> getAllUsersRequestsTools() {
        List<UsersRequestsToolsEntity> usersRequestsToolsEntities = iUsersRequestsToolsRepository.findAll();
        List<UsersRequestsToolsDTO> usersRequestsToolsDTOS = usersRequestsToolsEntities
                .stream()
                .map(I_USERS_REQUESTS_TOOLS_MAPPER::usersRequestsToolsEntityToUsersRequestsToolsDTO)
                .collect(Collectors.toList());
        if (usersRequestsToolsDTOS.isEmpty()) {
            throw new RecordNotFoundException("Sorry, No DATA Found!...");
        }
        return new ResponseEntity<>(usersRequestsToolsDTOS, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UsersRequestsToolsDTO> createNewRequestTools(UsersRequestsToolsDTO newUsersRequestsToolsDTO) {

        final UsersRequestsToolsEntity usersRequestsToolsEntity = I_USERS_REQUESTS_TOOLS_MAPPER.handasatToolsDTOToUsersRequestsToolsEntity(newUsersRequestsToolsDTO);
        final UsersRequestsToolsEntity createUsersRequestsToolsEntity = this.iUsersRequestsToolsRepository.save(usersRequestsToolsEntity);
        UsersRequestsToolsDTO usersRequestsToolsDTO = I_USERS_REQUESTS_TOOLS_MAPPER.usersRequestsToolsEntityToUsersRequestsToolsDTO(createUsersRequestsToolsEntity);
        return new ResponseEntity<>(usersRequestsToolsDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UsersRequestsToolsDTO> updateRequestTools(Long id, UsersRequestsToolsDTO newUsersRequestsToolsDTO) {

        Optional<UsersRequestsToolsEntity> usersRequestsToolsEntity = iUsersRequestsToolsRepository.findById(id);
        if (usersRequestsToolsEntity.isEmpty()) {
            throw new RecordNotFoundException("the item with id: " + id + " not found!...");
        }
        UsersRequestsToolsEntity exitingUsersRequestsToolsEntity = usersRequestsToolsEntity.get();
        exitingUsersRequestsToolsEntity.setToolName(newUsersRequestsToolsDTO.getToolName());
        exitingUsersRequestsToolsEntity.setToolQty(newUsersRequestsToolsDTO.getToolQty());

        UsersRequestsToolsEntity updateUsersRequestsToolsEntity = iUsersRequestsToolsRepository.save(exitingUsersRequestsToolsEntity);
        UsersRequestsToolsDTO usersRequestsToolsDTO = I_USERS_REQUESTS_TOOLS_MAPPER.usersRequestsToolsEntityToUsersRequestsToolsDTO(updateUsersRequestsToolsEntity);
        return new ResponseEntity<>(usersRequestsToolsDTO, HttpStatus.OK);
    }






}
