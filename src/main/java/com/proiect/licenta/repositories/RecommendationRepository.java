package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Professor;
import com.proiect.licenta.entities.Recommendation;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends Repository<Recommendation, Integer> {

    Recommendation save(Recommendation recommendation);
    List<Recommendation> findAll();
    List<Recommendation> findAllByAppUser(AppUser appUser);
    List<Recommendation> findAllByCategory(String cat);
    Optional<Recommendation> findById(int id);
    void delete(Recommendation recommendation);
}
