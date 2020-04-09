package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Assigment;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AssigmentRepository extends Repository<Assigment, Integer> {
    Assigment save(Assigment assigment);
    List<Assigment> findAll();
    Optional<Assigment> findById(int id);
    List<Assigment> findByAllCoursesAndAppUser(AllCourses allCourses, AppUser appUser);
    List<Assigment> findAllByAppUser(AppUser appUser);
    void delete(Assigment assigment);
}
