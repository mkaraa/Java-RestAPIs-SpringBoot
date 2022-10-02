package com.example.testcase.repository;

import com.example.testcase.model.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
    //   List<Citizen> findCitizensByNameContains(String name);
    //   Citizen findCitizensByCitizenId(int citizenId);
}
