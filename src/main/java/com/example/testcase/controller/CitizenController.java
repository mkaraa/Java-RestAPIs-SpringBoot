package com.example.testcase.controller;

import com.example.testcase.model.dto.CitizenDTO;
import com.example.testcase.service.CitizenService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/citizens")
@Api(value = "Citizen Controller")
public class CitizenController {
    private final CitizenService citizenService;

    @PostMapping
    public ResponseEntity<CitizenDTO> save(@RequestBody CitizenDTO citizenDTO){
        return new ResponseEntity<>(citizenService.save(citizenDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CitizenDTO> update(@PathVariable("id") int id,@RequestBody CitizenDTO citizenDTO){
        return new ResponseEntity<>(citizenService.update(id,citizenDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/isCitizen")
    public ResponseEntity<List<CitizenDTO>> findCitizenByIsCitizen(@RequestBody Boolean isCitizen){
        return new ResponseEntity<>(citizenService.findCitizenByIsCitizen(isCitizen),HttpStatus.OK);
    }

    @GetMapping(value = "/hasDrivingLicense")
    public ResponseEntity<List<CitizenDTO>> findCitizenByHasDrivingLicense(@RequestBody Boolean hasDrivingLicense){
        return new ResponseEntity<>(citizenService.findCitizenByHasDrivingLicense(hasDrivingLicense),HttpStatus.OK);
    }

    @GetMapping(value = "/findByNameContains/{name}")
    public ResponseEntity<List<CitizenDTO>> findCitizensByNameContains(@PathVariable("name") String name){
        return new ResponseEntity<>(citizenService.findCitizensByNameContains(name),HttpStatus.OK);
    }

    @GetMapping(value = "/findCitizensByCitizenId/{id}")
    public ResponseEntity<Object> findCitizensByCitizenId(@PathVariable("id") int id){
        return new ResponseEntity<>(citizenService.findCitizensByCitizenId(id),HttpStatus.OK);
    }

    @GetMapping(value = "/findNumberOfCitizensChild")
    public ResponseEntity<Map<String, Integer>> findNumberOfCitizensChild(){
        return new ResponseEntity<>(citizenService.findNumberOfCitizensChild(),HttpStatus.OK);
    }
}
