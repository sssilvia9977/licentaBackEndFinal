package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.CourseLaboratory;
import com.proiect.licenta.entities.Schedule;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends Repository<Schedule, Integer> {

    Schedule save(Schedule schedule);
    List<Schedule> findAll();
    Optional<Schedule> findById(int id);
    List<Schedule> findByAppUser(AppUser appUser);
    void delete(Schedule schedule);
}
