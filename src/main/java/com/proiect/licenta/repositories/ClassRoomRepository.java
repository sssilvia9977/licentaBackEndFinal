package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.ClassRoom;
import com.proiect.licenta.entities.CourseLaboratory;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ClassRoomRepository extends Repository<ClassRoom, Integer> {

    ClassRoom save(ClassRoom classRoom);
    List<ClassRoom> findAll();
    Optional<ClassRoom> findById(int id);
    Optional<ClassRoom> findByClassRoomNameAndAddress(String classRoomName, String address);
    Optional<ClassRoom> findByClassRoomName(String name);

    void delete(ClassRoom classRoom);

}
