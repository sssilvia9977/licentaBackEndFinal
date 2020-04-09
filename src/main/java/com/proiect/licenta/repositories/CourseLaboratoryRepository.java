package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.ClassRoom;
import com.proiect.licenta.entities.CourseLaboratory;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CourseLaboratoryRepository extends Repository<CourseLaboratory, Integer> {

    CourseLaboratory save(CourseLaboratory courseLaboratory);
    List<CourseLaboratory> findAll();
    Optional<CourseLaboratory> findById(int id);
    Optional<CourseLaboratory> findByAllCourses_Id(int id);
    void delete(CourseLaboratory courseLaboratory);

}
