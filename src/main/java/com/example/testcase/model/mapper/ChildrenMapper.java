package com.example.testcase.model.mapper;

import com.example.testcase.model.dto.ChildrenDTO;
import com.example.testcase.model.entity.Children;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildrenMapper {
    private ChildrenMapper(){
        log.info("CANNOT INITIALIZED");
    }

    public static ChildrenDTO toDTO(Children children){
        return ChildrenDTO.builder()
                .id(children.getChildId())
                .name(children.getName())
                .build();
    }

    public static Children toEntity(ChildrenDTO childrenDTO){
        return Children.builder()
                .childId(childrenDTO.getId())
                .name(childrenDTO.getName())
                .build();
    }
}
