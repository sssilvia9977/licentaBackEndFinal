package com.proiect.licenta.controller;

import com.proiect.licenta.dto.*;
import com.proiect.licenta.entities.*;
import com.proiect.licenta.entities.choices.AssigStatus;
import com.proiect.licenta.entities.choices.DaysOfWeek;
import com.proiect.licenta.services.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserDetailsController {

    private final AppUserServices appUserService;
    private final AllCoursesService allCoursesService;
    private final StructuraAnUniverService structuraAnUniverService;
    private final AssigmentService assigmentService;
    private final BuildDTOs buildDTOs;

    //TODO: cand adaugi un user nou, universitatea gaseste o in functie de facultate, ca nu poti sa ai un user la facX,univZ
    // facX sa nu aparatina la univZ

    @PostMapping(value = "/getUsername")
    public AppUserDTO returnUsername(@RequestBody SessionID sessionID){

        AppUserDTO appUserDTO = new AppUserDTO();
        int id = sessionID.getSessionId();

        if(appUserService.findAppUserById(id).isPresent()){
            AppUser appUser = appUserService.findAppUserById(id).get();

            appUserDTO.setEmail(appUser.getEmail());
            appUserDTO.setFacultyName(appUser.getFaculty().toString());
            appUserDTO.setFirstName(appUser.getFirstName());
            appUserDTO.setLastName(appUser.getLastName());
            appUserDTO.setUniversityName(appUser.getUniversity().toString());
            appUserDTO.setYearOfStudy(appUser.getYearOfStudy());
            appUserDTO.setUsername(appUser.getUsername());

        }
        return appUserDTO;
    }


    @PostMapping(value = "/getCourses")
    public List<CourseDTO> getAllCourses(@RequestBody SessionID sessionID){
        List<CourseDTO> courseDTOS = new ArrayList<>();

        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        List<AllCourses> currentUserCourses = appUserService.findAllCoursesByCurrentUser(appUser);
        for (AllCourses a: currentUserCourses) {
            String[] abreviere = a.getName().split(" ");
            String abreviereFinal = "";
            for (String s: abreviere) {
                abreviereFinal += s.substring(0,1).toUpperCase();
            }
            courseDTOS.add(new CourseDTO(a.getName(), abreviereFinal));
        }
        return courseDTOS;
    }


    @PostMapping("/getCourseDetails")
    public CourseDTO getCourseDetails(@RequestBody SessionID sessionID){
        /*
        Ce trimit de pe fe, e numele intreg al cursului

        TODO: assgments pe un alt url eventual
         */

        AllCourses currentCourse = allCoursesService.findACourseByName(sessionID.getCourseName()).get();

        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        CourseDTO courseDTO = buildDTOs.makeCourseDetailsDTO(appUser,currentCourse);
        return courseDTO;


    }


    @PostMapping(value = "/getSchedule")
    public List<ScheduleDTO> getSchedule(@RequestBody SessionID sessionID){

        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        return buildDTOs.buildScheduleDTO(appUser);

    }


    @PostMapping("/getStructAnUniv")
    public List<StructAnUnivDTO> getStructAn(@RequestBody SessionID sessionID){
        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        University university  = appUser.getUniversity();
        List<StructuraAnUniversitar> structuraAnUniversitars = structuraAnUniverService.findStructuraByUniversity(university);
        List<StructAnUnivDTO> structAnUnivDTOS = new ArrayList<>();
        for(StructuraAnUniversitar s: structuraAnUniversitars){
            structAnUnivDTOS.add(buildDTOs.makeStructAnUnivDTO(s));
        }

        return structAnUnivDTOS;

    }

    @PostMapping("/setAssigStatus")
    public void setAssigStatus(@RequestBody SessionID sessionID){
        String idIntermediat = sessionID.getAssigId();
        int id = Integer.parseInt(idIntermediat);
        Assigment assig = assigmentService.findAssigmentById(id).get();

        assigmentService.setStatus(assig);
    }


    @PostMapping("/addAssig")
    public int addAssig(@RequestBody SessionID sessionID) throws ParseException {
        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        AllCourses currentCourse = allCoursesService.findACourseByName(sessionID.getCourseName()).get();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date assigDate = formatter.parse(sessionID.getAssigDeadline());
        return assigmentService.save(new Assigment(null,assigDate ,sessionID.getAssigTitle(), sessionID.getAssigDescription(),
                AssigStatus.NOTCompleted, appUser, currentCourse)).getId();



    }

    @PostMapping("/deleteAssig")
    public void deleteAssig(@RequestBody SessionID sessionID) throws ParseException {
        String idIntermediat = sessionID.getAssigId();
        int id = Integer.parseInt(idIntermediat);
        Assigment assig = assigmentService.findAssigmentById(id).get();
        assigmentService.deleteAssigment(assig);
    }


    @PostMapping("/updateAssig")
    public void updateAssig(@RequestBody SessionID sessionID) throws ParseException {
        String idIntermediat = sessionID.getAssigId();
        int id = Integer.parseInt(idIntermediat);
        Assigment assig = assigmentService.findAssigmentById(id).get();

        String newTitle = sessionID.getAssigTitle();
        String newDesc = sessionID.getAssigDescription();
        String newDeadLine = sessionID.getAssigDeadline();
        Date assigDate =new SimpleDateFormat("dd-MM-yyyy").parse(newDeadLine);

        assig.setAssigName(newTitle);
        assig.setDeadline(assigDate);
        assig.setDescription(newDesc);

        assigmentService.save(assig);
    }


    @PostMapping("/getAllAssigs")
    public List<AssigmentDTO>  getAllAssigs(@RequestBody SessionID sessionID){
        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();
        List<Assigment> assigments = assigmentService.findAssigByUser(appUser);
        if(assigments.isEmpty())
            return null;
        else{
            List<AssigmentDTO> assigmentDTOS = new ArrayList<>();
            for(Assigment a: assigments){
                assigmentDTOS.add(buildDTOs.makeAssigDTO(a.getAllCourses(), a));
            }
            return assigmentDTOS;
        }

    }





}
