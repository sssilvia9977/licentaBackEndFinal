package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.*;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends Repository<Exam, Integer> {


    Exam save(Exam exam);
    List<Exam> findAll();
    List<Exam> findAllByAllCourses(AllCourses allCourses);
    Optional<Exam> findById(int id);
    List<Exam> findByAppUser(AppUser appUser);
    void delete(Exam exam);
    List<Exam> findByAllCoursesAndAppUser(AllCourses allCourses, AppUser appUser);


}
