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

    public AllCourses save(AllCourses allCourses) {
        return repositoryFactory.createAllCoursesRepository().save(allCourses);
    }

    public Optional<AllCourses> findAllCourseById(int id) {
        return repositoryFactory.createAllCoursesRepository().findById(id);
    }

    public List<AllCourses> findAllAllCourses() {
        return repositoryFactory.createAllCoursesRepository().findAll();
    }

    public void deleteAllCourse(AllCourses allCourses) {
        repositoryFactory.createAllCoursesRepository().delete(allCourses);
    }

    public Optional<AllCourses> findACourseByName(String name) {
        return repositoryFactory.createAllCoursesRepository().findByName(name);
    }

    public Optional<AllCourses> findCourseByNameAndAppUser(String name, AppUser appUser) {
        return repositoryFactory.createAllCoursesRepository().findByNameAndAppUser(name, appUser);
    }


    public List<AllCourses> findAllAllCoursesByUser(AppUser appUser) {
        return repositoryFactory.createAllCoursesRepository().findAllByAppUser(appUser);
    }




}

