package com.proiect.licenta.controller;

import com.proiect.licenta.dto.*;
import com.proiect.licenta.entities.*;
import com.proiect.licenta.entities.choices.AssigStatus;
import com.proiect.licenta.entities.choices.DaysOfWeek;
import com.proiect.licenta.services.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    private final RecommendationService recommendationService;
    private final FacultyService facultyService;
    private final UniversityService universityService;


    //TODO: cand adaugi un user nou, universitatea gaseste o in functie de facultate, ca nu poti sa ai un user la facX,univZ
    // facX sa nu aparatina la univZ

    @PostMapping(value = "/getUsername")
    public AppUserDTO returnUsername(@RequestBody SessionID sessionID) {

        AppUserDTO appUserDTO = new AppUserDTO();
        int id = sessionID.getSessionId();

        if (appUserService.findAppUserById(id).isPresent()) {
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

    @PostMapping(value = "/updateUser")
    public String updateUser(@RequestBody SessionID sessionID) {
        AppUser appUser = appUserService.findAppUserById(sessionID.getSessionId()).get();
        String status = "ok";

        if (Optional.ofNullable(sessionID.getUsername()).isPresent()) {
            if (appUserService.checkUniqueUsername(sessionID.getUsername())) {
                appUser.setUsername(sessionID.getUsername());
                appUserService.save(appUser);
            } else {
                status = "Username already exists. Please choose another one.";
            }
        }
        if (Optional.ofNullable(sessionID.getPassword()).isPresent() ) {
            if (sessionID.getPassword().length() < 6) {
                status = "Please provide a password with at least than 6 characters.";
            } else {
                appUser.setUsername(sessionID.getPassword());
                appUserService.save(appUser);
            }
        }
        if (Optional.ofNullable(sessionID.getEmail()).isPresent() ) {
            if (sessionID.getEmail().length() > 4 && sessionID.getEmail().contains("@")) {
                appUser.setEmail(sessionID.getEmail());
                appUserService.save(appUser);
            } else {
                status = "Please provide e valid email address";
            }
        }
        if (Optional.ofNullable(sessionID.getFaculty()).isPresent() ) {
            if (facultyService.findAllFaculties().stream().noneMatch(e -> e.getFullName().equals(sessionID.getFaculty()))) {
                Faculty faculty = new Faculty(null, null, null, null, sessionID.getFaculty(), buildDTOs.abreviere(sessionID.getFaculty()), null);
                facultyService.save(faculty);
                appUser.setFaculty(faculty);
                appUserService.save(appUser);
            } else {
                Faculty faculty = facultyService.findFacultyName(sessionID.getFaculty()).get();
                appUser.setFaculty(faculty);
                appUserService.save(appUser);
            }
        }
        if ( Optional.ofNullable(sessionID.getUni()).isPresent()) {
            if (universityService.findAllUniversities().stream().noneMatch(e -> e.getFullName().equals(sessionID.getUni()))) {
                University university = new University(null, sessionID.getUni(), buildDTOs.abreviere(sessionID.getUni()),null, null);
                universityService.save(university);
                appUser.setUniversity(university);
                appUserService.save(appUser);
            } else {
                University university = universityService.findUniversityByName(sessionID.getUni()).get();
                appUser.setUniversity(university);
                appUserService.save(appUser);
            }
        }
        return status;
    }


    @GetMapping(value = "getAllUnis")
    public List<String> getAllUnis() {
        List<String> unis = new ArrayList<>();
        universityService.findAllUniversities().forEach(e -> {
            unis.add(e.getFullName());
        });
        return unis;
    }

    @GetMapping(value = "getAllFaculties")
    public List<String> getAllFaculties() {
        List<String> fac = new ArrayList<>();
        facultyService.findAllFaculties().forEach(e -> {
            fac.add(e.getFullName());
        });
        return fac;
    }

    @PostMapping(value = "/getCourses")
    public List<CourseDTO> getAllCourses(@RequestBody SessionID sessionID) {
        List<CourseDTO> courseDTOS = new ArrayList<>();

        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        List<AllCourses> currentUserCourses = appUserService.findAllCoursesByCurrentUser(appUser);
        for (AllCourses a : currentUserCourses) {
            String[] abreviere = a.getName().split(" ");
            String abreviereFinal = "";
            for (String s : abreviere) {
                abreviereFinal += s.substring(0, 1).toUpperCase();
            }
            courseDTOS.add(new CourseDTO(a.getName(), abreviereFinal));
        }
        return courseDTOS;
    }


    @PostMapping("/getCourseDetails")
    public CourseDTO getCourseDetails(@RequestBody SessionID sessionID) {
        /*
        Ce trimit de pe fe, e numele intreg al cursului

        TODO: assgments pe un alt url eventual
         */

        AllCourses currentCourse = allCoursesService.findACourseByName(sessionID.getCourseName()).get();

        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        CourseDTO courseDTO = buildDTOs.makeCourseDetailsDTO(appUser, currentCourse);
        return courseDTO;


    }


    @PostMapping(value = "/getSchedule")
    public List<ScheduleDTO> getSchedule(@RequestBody SessionID sessionID) {

        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        return buildDTOs.buildScheduleDTO(appUser);

    }


    @PostMapping("/getStructAnUniv")
    public List<StructAnUnivDTO> getStructAn(@RequestBody SessionID sessionID) {
        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();

        University university = appUser.getUniversity();
        List<StructuraAnUniversitar> structuraAnUniversitars = structuraAnUniverService.findStructuraByUniversity(university);
        List<StructAnUnivDTO> structAnUnivDTOS = new ArrayList<>();
        for (StructuraAnUniversitar s : structuraAnUniversitars) {
            structAnUnivDTOS.add(buildDTOs.makeStructAnUnivDTO(s));
        }

        return structAnUnivDTOS;

    }

    @PostMapping("/setAssigStatus")
    public void setAssigStatus(@RequestBody SessionID sessionID) {
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
        return assigmentService.save(new Assigment(null, assigDate, sessionID.getAssigTitle(), sessionID.getAssigDescription(),
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
        Date assigDate = new SimpleDateFormat("dd-MM-yyyy").parse(newDeadLine);

        assig.setAssigName(newTitle);
        assig.setDeadline(assigDate);
        assig.setDescription(newDesc);

        assigmentService.save(assig);
    }


    @PostMapping("/getAllAssigs")
    public List<AssigmentDTO> getAllAssigs(@RequestBody SessionID sessionID) {
        int id = sessionID.getSessionId();
        AppUser appUser = appUserService.findAppUserById(id).get();
        List<Assigment> assigments = assigmentService.findAssigByUser(appUser);
        if (assigments.isEmpty())
            return null;
        else {
            List<AssigmentDTO> assigmentDTOS = new ArrayList<>();
            for (Assigment a : assigments) {
                assigmentDTOS.add(buildDTOs.makeAssigDTO(a.getAllCourses(), a));
            }
            return assigmentDTOS;
        }

    }


}
