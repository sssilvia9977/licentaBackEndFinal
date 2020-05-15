package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.StructuraAnUniversitar;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface StructuraAnUniversitarRepository extends Repository<StructuraAnUniversitar,Integer> {
    StructuraAnUniversitar save(StructuraAnUniversitar structuraAnUniversitar);
    List<StructuraAnUniversitar> findAll();
    Optional<StructuraAnUniversitar> findById(int id);
    List<StructuraAnUniversitar> findByAppUser(AppUser appUser);
    void delete(StructuraAnUniversitar structuraAnUniversitar);
}
