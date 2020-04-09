package com.proiect.licenta.services;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.Professor;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final RepositoryFactory repositoryFactory;

    public Professor save(Professor professor){
        return repositoryFactory.createProfessorRepository().save(professor);
    }
    public Optional<Professor> findProfessorById(int id){
        return repositoryFactory.createProfessorRepository().findById(id);
    }
    public List<Professor> findAllProfessors(){
        return repositoryFactory.createProfessorRepository().findAll();
    }
    public void deleteProfessor(Professor professor){
        repositoryFactory.createProfessorRepository().delete(professor);
    }
    public Optional<Professor> findProfessorByName(String name){
        return repositoryFactory.createProfessorRepository().findByName(name);
    }
}
