package com.proiect.licenta.services;


import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Professor;
import com.proiect.licenta.entities.Recommendation;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RepositoryFactory repositoryFactory;

    public Recommendation save(Recommendation recommendation){
        return repositoryFactory.createRecommendationRepository().save(recommendation);
    }
    public Optional<Recommendation> findRecommendationById(int id){
        return repositoryFactory.createRecommendationRepository().findById(id);
    }
    public List<Recommendation> findAllRecommendation(){
        return repositoryFactory.createRecommendationRepository().findAll();
    }

    public List<Recommendation> findAllRecommendationsByUser(AppUser appUser){
        return repositoryFactory.createRecommendationRepository().findAllByAppUser(appUser);
    }

    public List<Recommendation> findAllRecommendationByCategory(String category){
        return repositoryFactory.createRecommendationRepository().findAllByCategory(category);
    }


    public void deleteRecommendation(Recommendation recommendation){
        repositoryFactory.createRecommendationRepository().delete(recommendation);
    }




}
