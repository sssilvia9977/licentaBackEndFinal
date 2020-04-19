package com.proiect.licenta.services;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.CourseLaboratory;
import com.proiect.licenta.entities.CourseSeminary;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseSeminaryService {
    private final RepositoryFactory repositoryFactory;

    public CourseSeminary save(CourseSeminary courseSeminary){
        return repositoryFactory.createCourseSeminaryRepository().save(courseSeminary);
    }
    public Optional<CourseSeminary> findCourseSeminaryById(int id){
        return repositoryFactory.createCourseSeminaryRepository().findById(id);
    }
    public List<CourseSeminary> findAllCourseSeminary(){
        return repositoryFactory.createCourseSeminaryRepository().findAll();
    }
    public void deleteCourseSeminary(CourseSeminary courseSeminary){
        repositoryFactory.createCourseSeminaryRepository().delete(courseSeminary);
    }



}
