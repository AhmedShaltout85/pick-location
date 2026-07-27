//package com.a08r.pick_location.models.mapper;
//
//import com.a08r.pick_location.models.dto.PickLocationDTO;
//import com.a08r.pick_location.models.location.PickLocationEntity;
//
//public class PickLocationMapperImpl implements IPickLocationMapper {
//    @Override
//    public PickLocationEntity pickLocationDTOToPickLocationEntity(PickLocationDTO pickLocationDTO) {
//        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
//        if(pickLocationDTO == null){
//            throw new NullPointerException("pickLocationDTO should not be null");
//        }
//        PickLocationEntity pickLocationEntity = new PickLocationEntity();
//        pickLocationEntity.setId(pickLocationDTO.getId());
//        pickLocationEntity.setAddress(pickLocationDTO.getAddress());
//        pickLocationEntity.setLatitude(pickLocationDTO.getLatitude());
//        pickLocationEntity.setLongitude(pickLocationDTO.getLongitude());
//        pickLocationEntity.setFlag(pickLocationDTO.getFlag());
//        pickLocationEntity.setGis_url(pickLocationDTO.getGis_url());
//        pickLocationEntity.setHandasahName(pickLocationDTO.getHandasah_name());
//        pickLocationEntity.setTechnicalName(pickLocationDTO.getTechnical_name());
//        pickLocationEntity.setIsFinished(pickLocationDTO.getIs_finished());
//        pickLocationEntity.setIsApproved(pickLocationDTO.getIs_approved());
//        pickLocationEntity.setCallerName(pickLocationDTO.getCaller_name());
//        pickLocationEntity.setBrokenType(pickLocationDTO.getBroker_type());
//        pickLocationEntity.setCallerNumber(pickLocationDTO.getCaller_phone());
//        pickLocationEntity.setVideoCall(pickLocationDTO.getVideo_call());
//        pickLocationEntity.setDate(pickLocationEntity.getDate());
//
//        return pickLocationEntity;
//    }
//
//    @Override
//    public PickLocationDTO pickLocationEntityToPickLocationDTO(PickLocationEntity pickLocationEntity) {
//
//        if(pickLocationEntity == null){
//            throw new NullPointerException("pickLocationEntity should not be null");
//        }
//
//        PickLocationDTO pickLocationDTO = new PickLocationDTO();
//        pickLocationDTO.setId(pickLocationEntity.getId());
//        pickLocationDTO.setAddress(pickLocationEntity.getAddress());
//        pickLocationDTO.setLatitude(pickLocationEntity.getLatitude());
//        pickLocationDTO.setLongitude(pickLocationEntity.getLongitude());
//        pickLocationDTO.setFlag(pickLocationEntity.getFlag());
//        pickLocationDTO.setGis_url(pickLocationEntity.getGis_url());
//        pickLocationDTO.setHandasah_name(pickLocationEntity.getHandasahName());
//        pickLocationDTO.setTechnical_name(pickLocationEntity.getTechnicalName());
//        pickLocationDTO.setIs_finished(pickLocationEntity.getIsFinished());
//        pickLocationDTO.setIs_approved(pickLocationEntity.getIsApproved());
//        pickLocationDTO.setCaller_name(pickLocationEntity.getCallerName());
//        pickLocationDTO.setBroker_type(pickLocationEntity.getBrokenType());
//        pickLocationDTO.setCaller_phone(pickLocationEntity.getCallerNumber());
//        pickLocationDTO.setVideo_call(pickLocationEntity.getVideoCall());
//        pickLocationDTO.setDate(pickLocationEntity.getDate());
//
//        return pickLocationDTO;
//
//    }
//}
package com.a08r.pick_location.models.mapper;

import com.a08r.pick_location.models.dto.PickLocationDTO;
import com.a08r.pick_location.models.location.PickLocationEntity;

public class PickLocationMapperImpl implements IPickLocationMapper {
    @Override
    public PickLocationEntity pickLocationDTOToPickLocationEntity(PickLocationDTO pickLocationDTO) {
        //updated for unit testing (shouldThrowNotNullExceptionWhenLabsDTOisNull-- method)
        if (pickLocationDTO == null) {
            throw new NullPointerException("pickLocationDTO should not be null");
        }
        PickLocationEntity pickLocationEntity = new PickLocationEntity();
        pickLocationEntity.setId(pickLocationDTO.getId());
        pickLocationEntity.setAddress(pickLocationDTO.getAddress());
        pickLocationEntity.setLatitude(pickLocationDTO.getLatitude());
        pickLocationEntity.setLongitude(pickLocationDTO.getLongitude());
        pickLocationEntity.setFlag(pickLocationDTO.getFlag());
        pickLocationEntity.setGis_url(pickLocationDTO.getGis_url());
        pickLocationEntity.setHandasahName(pickLocationDTO.getHandasah_name());
        pickLocationEntity.setTechnicalName(pickLocationDTO.getTechnical_name());
        pickLocationEntity.setIsFinished(pickLocationDTO.getIs_finished());
        pickLocationEntity.setIsApproved(pickLocationDTO.getIs_approved());
        pickLocationEntity.setCallerName(pickLocationDTO.getCaller_name());
        pickLocationEntity.setBrokenType(pickLocationDTO.getBroker_type());
        pickLocationEntity.setCallerNumber(pickLocationDTO.getCaller_phone());
        pickLocationEntity.setVideoCall(pickLocationDTO.getVideo_call());

        // FIX: was pickLocationEntity.getDate() (always null on a fresh entity) —
        // must pull the date from the incoming DTO instead
        String date = pickLocationDTO.getDate();
        if (date == null || date.isBlank()) {
            // Fallback so a missing date from the client doesn't blow up the INSERT.
            // Change the format here if your column expects something specific.
            date = java.time.LocalDate.now().toString(); // e.g. "2026-07-27"
        }
        pickLocationEntity.setDate(date);

        return pickLocationEntity;
    }

    @Override
    public PickLocationDTO pickLocationEntityToPickLocationDTO(PickLocationEntity pickLocationEntity) {

        if (pickLocationEntity == null) {
            throw new NullPointerException("pickLocationEntity should not be null");
        }

        PickLocationDTO pickLocationDTO = new PickLocationDTO();
        pickLocationDTO.setId(pickLocationEntity.getId());
        pickLocationDTO.setAddress(pickLocationEntity.getAddress());
        pickLocationDTO.setLatitude(pickLocationEntity.getLatitude());
        pickLocationDTO.setLongitude(pickLocationEntity.getLongitude());
        pickLocationDTO.setFlag(pickLocationEntity.getFlag());
        pickLocationDTO.setGis_url(pickLocationEntity.getGis_url());
        pickLocationDTO.setHandasah_name(pickLocationEntity.getHandasahName());
        pickLocationDTO.setTechnical_name(pickLocationEntity.getTechnicalName());
        pickLocationDTO.setIs_finished(pickLocationEntity.getIsFinished());
        pickLocationDTO.setIs_approved(pickLocationEntity.getIsApproved());
        pickLocationDTO.setCaller_name(pickLocationEntity.getCallerName());
        pickLocationDTO.setBroker_type(pickLocationEntity.getBrokenType());
        pickLocationDTO.setCaller_phone(pickLocationEntity.getCallerNumber());
        pickLocationDTO.setVideo_call(pickLocationEntity.getVideoCall());
        pickLocationDTO.setDate(pickLocationEntity.getDate());

        return pickLocationDTO;
    }
}