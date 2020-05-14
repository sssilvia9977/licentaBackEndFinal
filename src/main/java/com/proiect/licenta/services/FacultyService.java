package com.proiect.licenta.services;


import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Faculty;
import com.proiect.licenta.repositories.FacultyRepository;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final RepositoryFactory repositoryFactory;

    public Faculty save(Faculty faculty){
        return repositoryFactory.createFacultyRepository().save(faculty);
    }
    public Optional<Faculty> findFacultyById(int id){
        return repositoryFactory.createFacultyRepository().findByFacultyId(id);
    }
    public List<Faculty> findAllFaculties(){
        return repositoryFactory.createFacultyRepository().findAll();
    }
    public void deleteFaculty(Faculty faculty){
        repositoryFactory.createFacultyRepository().delete(faculty);
    }

    public Optional<Faculty> findFacultyName(String name){
        return repositoryFactory.createFacultyRepository().findFacultyByFullName(name);
    }
}
