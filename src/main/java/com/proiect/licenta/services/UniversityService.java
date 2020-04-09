package com.proiect.licenta.services;

import com.proiect.licenta.entities.Faculty;
import com.proiect.licenta.entities.University;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final RepositoryFactory repositoryFactory;

    public University save(University university){
        return repositoryFactory.createUniversityRepository().save(university);
    }
    public Optional<University> findUniversityById(int id){
        return repositoryFactory.createUniversityRepository().findByUniId(id);
    }
    public List<University> findAllUniversities(){
        return repositoryFactory.createUniversityRepository().findAll();
    }
    public void deleteUniversity(University university){
        repositoryFactory.createUniversityRepository().delete(university);
    }

}
