package com.proiect.licenta.services;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.CourseLaboratory;
import com.proiect.licenta.entities.Professor;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllCoursesService {


    private final RepositoryFactory repositoryFactory;
    private final CourseLectureService courseLectureService;
    private final CourseLaboratoryService courseLaboratoryService;
    private final CourseSeminaryService courseSeminaryService;

    public AllCourses save(AllCourses allCourses){
        return repositoryFactory.createAllCoursesRepository().save(allCourses);
    }
    public Optional<AllCourses> findAllCourseById(int id){
        return repositoryFactory.createAllCoursesRepository().findById(id);
    }
    public List<AllCourses> findAllAllCourses(){
        return repositoryFactory.createAllCoursesRepository().findAll();
    }
    public void deleteAllCourse(AllCourses allCourses){
        repositoryFactory.createAllCoursesRepository().delete(allCourses);
    }

    public Optional<AllCourses> findACourseByName(String name){
        return repositoryFactory.createAllCoursesRepository().findByName(name);
    }




}
