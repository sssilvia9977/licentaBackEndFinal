package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.CourseLaboratory;
import com.proiect.licenta.entities.CourseLecture;
import com.proiect.licenta.entities.CourseSeminary;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CourseSeminaryRepository extends Repository<CourseSeminary, Integer> {

    CourseSeminary save(CourseSeminary courseSeminary);
    List<CourseSeminary> findAll();
    Optional<CourseSeminary> findById(int id);
    Optional<CourseSeminary> findByAllCourses_Id(int id);
    void delete(CourseSeminary courseSeminary);
}
