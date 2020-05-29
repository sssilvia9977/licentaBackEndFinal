package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.StructuraAnUniversitar;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StructuraAnUniversitarRepository extends Repository<StructuraAnUniversitar,Integer> {
    StructuraAnUniversitar save(StructuraAnUniversitar structuraAnUniversitar);
    List<StructuraAnUniversitar> findAll();
    Optional<StructuraAnUniversitar> findById(int id);
    Optional<StructuraAnUniversitar> findByPeriodStartAndPeriodEndAndSchoolPeriodTypeAndAppUser(Date periodStart, Date periodEnd, String schoolPeriodType, AppUser appUser);
    List<StructuraAnUniversitar> findByAppUser(AppUser appUser);
    void delete(StructuraAnUniversitar structuraAnUniversitar);
}
