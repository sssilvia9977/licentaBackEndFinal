package com.proiect.licenta.controller;


import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Recommendation;
import com.proiect.licenta.entities.choices.RecCategories;
import com.proiect.licenta.services.AppUserServices;
import com.proiect.licenta.services.RecommendationService;
import com.proiect.licenta.services.SessionID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final AppUserServices appUserServices;


    @PostMapping(value = "/getAllRec")
    public List<Recommendation> getAllRec(){
        return recommendationService.findAllRecommendation();
    }

    @PostMapping(value = "/getRecPostedByUser")
    public List<Recommendation> getRecPostedByUser(@RequestBody SessionID sessionID){
        AppUser appUser = appUserServices.findAppUserById(sessionID.getSessionId()).get();
        return recommendationService.findAllRecommendationsByUser(appUser);
    }

    @PostMapping(value = "/addRec")
    public void addRec(@RequestBody SessionID sessionID){
        AppUser appUser = appUserServices.findAppUserById(sessionID.getSessionId()).get();
        Recommendation recommendation = new Recommendation(null, sessionID.getPlaceName(), sessionID.getAddress(), sessionID.getInitialComment(), sessionID.getCategory(), appUser);
        recommendationService.save(recommendation);
    }

    @DeleteMapping(value = "/deleteRec")
    public void deleteRec(@RequestBody SessionID sessionID){
        recommendationService.deleteRecommendation(recommendationService.findRecommendationById(sessionID.getRecId()).get());
    }

    @PostMapping(value = "/getAllCategories")
    public List<String> getRecEAT_CHEAP(){
        List<String> categ = new ArrayList<>();
        categ.add(RecCategories.SPLURGE);
        categ.add(RecCategories.COFFEE_STUDY);
        categ.add(RecCategories.COFFEE_TOGO);
        categ.add(RecCategories.CONCERT);
        categ.add(RecCategories.DISCO);
        categ.add(RecCategories.EAT_CHEAP);
        categ.add(RecCategories.RESTAURANT);
        Collections.sort(categ);
        return categ;
    }

    @PostMapping(value = "/getRecEAT_CHEAP")
    public List<Recommendation> getRecEAT_CHEAP(SessionID sessionID){
        return recommendationService.findAllRecommendationByCategory(sessionID.getCategory());
    }

    @PostMapping(value = "/getRecDISCO")
    public List<Recommendation> getRecDISCO(SessionID sessionID){
        return recommendationService.findAllRecommendationByCategory(sessionID.getCategory());
    }

    @PostMapping(value = "/getRecCONCERT")
    public List<Recommendation> getRecCONCERT(SessionID sessionID){
        return recommendationService.findAllRecommendationByCategory(sessionID.getCategory());
    }

    @PostMapping(value = "/getRecSPLURGE")
    public List<Recommendation> getRecSPLURGE(SessionID sessionID){
        return recommendationService.findAllRecommendationByCategory(sessionID.getCategory());
    }

    @PostMapping(value = "/getRecCOFFEE_STUDY")
    public List<Recommendation> getRecCOFFEE_STUDY(SessionID sessionID){
        return recommendationService.findAllRecommendationByCategory(sessionID.getCategory());
    }

    @PostMapping(value = "/getRecCOFFEE_TOGO")
    public List<Recommendation> getRecCOFFEE_TOGO(SessionID sessionID){
        return recommendationService.findAllRecommendationByCategory(sessionID.getCategory());
    }

    @PostMapping(value = "/getRecRESTAURANT")
    public List<Recommendation> getRecRESTAURANT(SessionID sessionID){
        return recommendationService.findAllRecommendationByCategory(sessionID.getCategory());
    }


}
