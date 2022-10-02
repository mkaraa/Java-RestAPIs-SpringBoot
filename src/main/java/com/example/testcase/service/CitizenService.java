package com.example.testcase.service;

import com.example.testcase.model.dto.CitizenDTO;
import com.example.testcase.model.dto.ResponseEntityClassDTO;
import com.example.testcase.model.entity.Citizen;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CitizenService {

    CitizenDTO save(CitizenDTO citizenDTO);

    CitizenDTO update(int id, CitizenDTO citizenDTO);

    List<CitizenDTO> findCitizenByIsCitizen(boolean isCitizen);

    List<CitizenDTO> findCitizenByHasDrivingLicense(boolean hasDrivingLicense);

    List<CitizenDTO> findCitizensByNameContains(String name);

    ResponseEntityClassDTO findCitizensByCitizenId(int citizenId);

    Map<String, Integer> findNumberOfCitizensChild();
}
