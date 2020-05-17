package com.proiect.licenta.services;


import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Assigment;
import com.proiect.licenta.entities.Exam;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final RepositoryFactory repositoryFactory;

    public Exam save(Exam exam){
        return repositoryFactory.createExamRepository().save(exam);
    }

    public Optional<Exam> findExamById(int id){
        return repositoryFactory.createExamRepository().findById(id);
    }

    public List<Exam> findAllExams(){
        return repositoryFactory.createExamRepository().findAll();
    }

    public void deleteExam(Exam exam){
        repositoryFactory.createExamRepository().delete(exam);
    }

    public Optional<Exam> findExamForSpecificCourseAndUser(AllCourses theCourse, AppUser appUser){
        return repositoryFactory.createExamRepository().findByAllCoursesAndAppUser(theCourse, appUser);
    }

    public List<Exam> findExamByUser(AppUser appUser){
        return repositoryFactory.createExamRepository().findByAppUser(appUser);
    }



}
