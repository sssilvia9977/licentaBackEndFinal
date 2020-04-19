package com.proiect.licenta.services;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.CourseLecture;
import com.proiect.licenta.entities.Professor;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseLectureService {
    private final RepositoryFactory repositoryFactory;

    public CourseLecture save(CourseLecture allCourses){
        return repositoryFactory.createCourseLectureRepository().save(allCourses);
    }
    public Optional<CourseLecture> findCourseLectureById(int id){
        return repositoryFactory.createCourseLectureRepository().findById(id);
    }
    public List<CourseLecture> findAllCourseLecture(){
        return repositoryFactory.createCourseLectureRepository().findAll();
    }
    public void deleteCourseLecture(CourseLecture courseLecture){
        repositoryFactory.createCourseLectureRepository().delete(courseLecture);
    }




}
