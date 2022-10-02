package com.example.testcase.service.impl;

import com.example.testcase.exception.ErrorHandler;
import com.example.testcase.exception.NotFoundException;
import com.example.testcase.exception.RestCitizenTrackingException;
import com.example.testcase.model.dto.ChildrenDTO;
import com.example.testcase.model.dto.CitizenDTO;
import com.example.testcase.model.dto.ResponseEntityClassDTO;
import com.example.testcase.model.entity.Children;
import com.example.testcase.model.entity.Citizen;
import com.example.testcase.model.mapper.ChildrenMapper;
import com.example.testcase.model.mapper.CitizenMapper;
import com.example.testcase.repository.ChildrenRepository;
import com.example.testcase.repository.CitizenRepository;
import com.example.testcase.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {
    private final CitizenRepository citizenRepository;
    private final ChildrenRepository childrenRepository;

    @Override
    @Transactional
    public CitizenDTO save(CitizenDTO citizenDTO) {
        Citizen citizen = CitizenMapper.toEntity(citizenDTO);
        Citizen savedCitizen = citizenRepository.save(citizen);

        List<Children> childrenDTOS = citizenDTO.getChildren();
        List<Children> childrenList = new ArrayList<>();
        Children children = new Children();
        for (Children childrenDTO : childrenDTOS){
            children.setName(childrenDTO.getName());
            children.setCitizen(savedCitizen);
            childrenList.add(children);
        }
        childrenRepository.saveAll(childrenList);
        return citizenDTO;
    }

    @Override
    @Transactional
    public CitizenDTO update(int id, CitizenDTO citizenDTO) {
        Optional<Citizen> citizenRepositoryById = citizenRepository.findById(id);
        if(citizenRepositoryById.isEmpty()){
            throw new RestCitizenTrackingException("ID : "+ id + " NOT FOUND. ", 404);
        }
        Citizen citizen = citizenRepositoryById.get();
        citizen.setCitizenId(id);
        citizen.setBeingCitizen(citizenDTO.isBeingCitizen());
        citizen.setName(citizenDTO.getName());
        citizen.setHasDrivingLicense(citizenDTO.isHasDrivingLicense());
        Citizen citizenDb = citizenRepository.save(citizen);

        // TODO : CHECK CHILDREN TO UPDATE
        //Existing Child
        List<Children> children = citizen.getChildren();
        Children children1 = new Children();
        if(!children.isEmpty()){
            childrenRepository.deleteAll(children);
        }
        if(children.isEmpty() && !ObjectUtils.isEmpty(citizenDTO.getChildren())){
            for (Children c: citizenDTO.getChildren()){
                children1.setCitizen(citizenDb);
                children1.setName(citizenDTO.getName());
                children.add(children1);
            }
            childrenRepository.saveAll(children);
        }

        return citizenDTO;
    }

    @Override
    public List<CitizenDTO> findCitizenByIsCitizen(boolean isCitizen) {
        List<Citizen> allByCitizenIs = citizenRepository.findAll();
        List<CitizenDTO> citizenDTOS = new ArrayList<>();
        List<Citizen> collect = allByCitizenIs.stream().filter(m -> m.isBeingCitizen() == isCitizen).collect(Collectors.toList());
        for(Citizen citizen: collect){
            citizenDTOS.add(CitizenMapper.toDTO(citizen));
        }
        return citizenDTOS;
    }

    @Override
    public List<CitizenDTO> findCitizenByHasDrivingLicense(boolean hasDrivingLicense) {
        List<Citizen> allByCitizenIs = citizenRepository.findAll();
        List<CitizenDTO> citizenDTOS = new ArrayList<>();
        List<Citizen> collect = allByCitizenIs.stream().filter(m -> m.isHasDrivingLicense() == hasDrivingLicense).collect(Collectors.toList());
        for(Citizen citizen: collect){
            citizenDTOS.add(CitizenMapper.toDTO(citizen));
        }
        return citizenDTOS;
    }

    @Override
    public List<CitizenDTO> findCitizensByNameContains(String name) {
        List<Citizen> allByCitizenIs = citizenRepository.findAll();
        List<Citizen> collect = allByCitizenIs.stream().filter(citizen -> citizen.getName().contains(name)).collect(Collectors.toList());
        List<CitizenDTO> citizenDTOS = new ArrayList<>();
        for (Citizen c: collect){
            citizenDTOS.add(CitizenMapper.toDTO(c));
        }
        return citizenDTOS;
    }

    // INFO: THIS ResponseEntityClassDTO CLASS AND DTO CREATED TO RESPONSE AS A COMMON OBJECT OF CITIZEN AND CHILD
    @Override
    public ResponseEntityClassDTO findCitizensByCitizenId(int citizenId) {
        Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);
        Optional<Children> childrenOptional = childrenRepository.findById(citizenId);
        ResponseEntityClassDTO dto = new ResponseEntityClassDTO();
        if(citizenOptional.isPresent()){
            Citizen citizen = citizenOptional.get();
            dto.setId(citizen.getCitizenId());
            dto.setName(citizen.getName());
            dto.setCitizenOrChild("CITIZEN");
        } else if(childrenOptional.isPresent()){
            Children children = childrenOptional.get();
            dto.setId(children.getChildId());
            dto.setName(children.getName());
            dto.setCitizenOrChild("CHILDREN");
        } else
            throw new RestCitizenTrackingException("ID : "+ citizenId + " NOT FOUND. ", 404);

        return dto;
    }

    @Override
    public Map<String, Integer> findNumberOfCitizensChild(){
        List<Citizen> citizenList = citizenRepository.findAll();
        if(CollectionUtils.isEmpty(citizenList)){
            new NotFoundException();
        }
        return citizenList.stream().parallel().collect(Collectors.toMap(Citizen::getName, citizen -> citizen.getChildren().size(), (a, b) -> b));
    }
}
