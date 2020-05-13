package com.proiect.licenta.controller;


import com.proiect.licenta.dto.RecDTO;
import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Recommendation;
import com.proiect.licenta.entities.choices.RecCategories;
import com.proiect.licenta.services.AppUserServices;
import com.proiect.licenta.services.RecommendationService;
import com.proiect.licenta.services.SessionID;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final AppUserServices appUserServices;

    @PostMapping(value = "/checkUniq")
    public boolean checkUniq(@RequestBody SessionID sessionID){
        boolean result = true;
        String[] newAddress = sessionID.getAddress().toUpperCase().replace(",.-", "").split(" ");
        List<Recommendation> recommendations = recommendationService.findAllRecommendation();
        for(Recommendation r: recommendations){
            String addressToCheck = r.getAddress().toUpperCase();
            int addressToCheckWords = addressToCheck.split(" ").length;
            int contor = 0;
            for(String s: newAddress){
                if(addressToCheck.contains(s)){
                    contor++;
                }
            }
            if(contor == addressToCheckWords){
                result = false;
            }
        }
        return result;
    }

    @GetMapping(value = "/getAllRec")
    public List<RecDTO> getAllRec(){
        return  recommendationService.findAllRecommendation().stream().map(RecDTO::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/getRecPostedByUser")
    public List<RecDTO> getRecPostedByUser(@RequestBody SessionID sessionID){
        AppUser appUser = appUserServices.findAppUserById(sessionID.getSessionId()).get();
        return recommendationService.findAllRecommendationsByUser(appUser).stream().map(RecDTO::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/addRec")
    public void addRec(@RequestBody SessionID sessionID){
        AppUser appUser = appUserServices.findAppUserById(sessionID.getSessionId()).get();
        Recommendation recommendation = new Recommendation(null, sessionID.getPlaceName(), sessionID.getAddress(), sessionID.getInitialComment(), sessionID.getCategory(), appUser);
        recommendationService.save(recommendation);
    }

    @DeleteMapping(value = "/deleteRec")
    public void deleteRec(@RequestBody SessionID sessionID){ ;
        recommendationService.deleteRecommendation(recommendationService.findRecommendationById(sessionID.getRecId()).get());
    }

    @GetMapping(value = "/getAllCategories")
    public List<String> getAllCategories(){
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

    @GetMapping(value = "/getRecEAT_CHEAP")
    public List<RecDTO> getRecEAT_CHEAP(){
        return recommendationService.findAllRecommendationByCategory(RecCategories.EAT_CHEAP).stream().map(RecDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/getRecDISCO")
    public List<RecDTO> getRecDISCO(){
        return recommendationService.findAllRecommendationByCategory(RecCategories.DISCO).stream().map(RecDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/getRecCONCERT")
    public List<RecDTO> getRecCONCERT(){
        return recommendationService.findAllRecommendationByCategory(RecCategories.CONCERT).stream().map(RecDTO::new).collect(Collectors.toList());
    }


    @GetMapping(value = "/getRecCOFFEE_STUDY")
    public List<RecDTO> getRecCOFFEE_STUDY(){
        return recommendationService.findAllRecommendationByCategory(RecCategories.COFFEE_STUDY).stream().map(RecDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/getRecCOFFEE_TOGO")
    public List<RecDTO> getRecCOFFEE_TOGO(){
        return recommendationService.findAllRecommendationByCategory(RecCategories.COFFEE_TOGO).stream().map(RecDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/getRecRESTAURANT")
    public List<RecDTO> getRecRESTAURANT(){
        return recommendationService.findAllRecommendationByCategory(RecCategories.RESTAURANT).stream().map(RecDTO::new).collect(Collectors.toList());
    }


    @GetMapping(value = "/getRecSPLURGE")
    public List<RecDTO> getRecSPLURGE(  ){
        return recommendationService.findAllRecommendationByCategory(RecCategories.SPLURGE).stream().map(RecDTO::new).collect(Collectors.toList());
    }



}
