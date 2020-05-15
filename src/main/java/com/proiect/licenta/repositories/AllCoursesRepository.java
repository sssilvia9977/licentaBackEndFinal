package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.AppUser;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AllCoursesRepository extends Repository<AllCourses, Integer> {

        AllCourses save(AllCourses allCourses);
        List<AllCourses> findAll();
        List<AllCourses> findAllByAppUser(AppUser appUser);
        Optional<AllCourses> findById(int id);
        Optional<AllCourses> findByName(String name);
        void delete(AllCourses allCourses);
}
