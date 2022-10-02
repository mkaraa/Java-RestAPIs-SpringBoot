package com.example.testcase.model.mapper;

import com.example.testcase.model.dto.ResponseEntityClassDTO;
import com.example.testcase.model.entity.ResponseEntityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseEntityClassMapper {
    private ResponseEntityClassMapper(){
        log.info("CANNOT INITIALIZED");
    }

    private static ResponseEntityClassDTO toDTO(ResponseEntityClass entity){
        return ResponseEntityClassDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .citizenOrChild(entity.getCitizenOrChild())
                .build();
    }

    private static ResponseEntityClass toEntity(ResponseEntityClassDTO dto){
        return ResponseEntityClass.builder()
                .id(dto.getId())
                .name(dto.getName())
                .citizenOrChild(dto.getCitizenOrChild())
                .build();
    }
}
