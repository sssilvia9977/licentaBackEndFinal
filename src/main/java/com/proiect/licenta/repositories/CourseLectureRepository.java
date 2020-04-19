package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.CourseLaboratory;
import com.proiect.licenta.entities.CourseLecture;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CourseLectureRepository extends Repository<CourseLecture, Integer> {

    CourseLecture save(CourseLecture courseLecture);
    List<CourseLecture> findAll();
    Optional<CourseLecture> findById(int id);
    void delete(CourseLecture courseLecture);
}
