package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.University;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends Repository<University, Integer> {

    University save(University university);
    List<University> findAll();
    Optional<University> findByUniId(int id);
    void delete(University university);


}
