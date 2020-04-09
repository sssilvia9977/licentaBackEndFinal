package com.proiect.licenta.services;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.CourseLaboratory;
import com.proiect.licenta.entities.CourseLecture;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseLaboratoryService {
    private final RepositoryFactory repositoryFactory;

    public CourseLaboratory save(CourseLaboratory courseLaboratory){
        return repositoryFactory.createCourseLaboratoryRepository().save(courseLaboratory);
    }
    public Optional<CourseLaboratory> findCourseLaboratoryById(int id){
        return repositoryFactory.createCourseLaboratoryRepository().findById(id);
    }
    public List<CourseLaboratory> findAllCourseLaboratory(){
        return repositoryFactory.createCourseLaboratoryRepository().findAll();
    }
    public void deleteCourseLaboratory(CourseLaboratory courseLaboratory){
        repositoryFactory.createCourseLaboratoryRepository().delete(courseLaboratory);
    }

    public Optional<CourseLaboratory> findLaboratoryForCourse(AllCourses allCourse){
        return repositoryFactory.createCourseLaboratoryRepository().findByAllCourses_Id(allCourse.getId());
    }


}
