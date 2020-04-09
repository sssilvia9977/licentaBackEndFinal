package com.proiect.licenta.repositories;


import com.proiect.licenta.entities.Faculty;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends Repository<Faculty, Integer> {

    Faculty save(Faculty faculty);
    List<Faculty> findAll();
    Optional<Faculty> findByFacultyId(int id);
    void delete(Faculty faculty);



}
