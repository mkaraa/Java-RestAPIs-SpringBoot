package com.example.testcase.model.mapper;

import com.example.testcase.model.dto.CitizenDTO;
import com.example.testcase.model.entity.Citizen;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CitizenMapper {
    private CitizenMapper(){
        log.info("CANNOT INITIALIZED");
    }

    public static CitizenDTO toDTO(Citizen entity) {
        return CitizenDTO.builder()
                .id(entity.getCitizenId())
                .name(entity.getName())
                .beingCitizen(entity.isBeingCitizen())
                .hasDrivingLicense(entity.isHasDrivingLicense())
                .children(entity.getChildren())
                .build();
    }

    public static Citizen toEntity(CitizenDTO dto){

        return Citizen.builder()
                .citizenId(dto.getId())
                .name(dto.getName())
                .beingCitizen(dto.isBeingCitizen())
                .hasDrivingLicense(dto.isHasDrivingLicense())
                .children(dto.getChildren())
                .build();
    }
}
