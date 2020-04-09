package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.Schedule;
import com.proiect.licenta.entities.StructuraAnUniversitar;
import com.proiect.licenta.entities.University;
import org.springframework.data.repository.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface StructuraAnUniversitarRepository extends Repository<StructuraAnUniversitar,Integer> {
    StructuraAnUniversitar save(StructuraAnUniversitar structuraAnUniversitar);
    List<StructuraAnUniversitar> findAll();
    Optional<StructuraAnUniversitar> findById(int id);
    List<StructuraAnUniversitar> findAllByUniversity(University university);
    void delete(StructuraAnUniversitar structuraAnUniversitar);
}
