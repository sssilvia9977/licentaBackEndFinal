package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.Professor;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository  extends Repository<Professor, Integer> {

    Professor save(Professor professor);
    List<Professor> findAll();
    Optional<Professor> findById(int id);
    Optional<Professor> findByName(String name);
    void delete(Professor professor);

}
