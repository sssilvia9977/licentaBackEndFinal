package com.proiect.licenta.dto;

import com.proiect.licenta.entities.*;
import com.proiect.licenta.services.AssigmentService;
import com.proiect.licenta.services.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RequiredArgsConstructor
@Data
@Service
public class BuildDTOs {

    private final ScheduleService scheduleService;
    private final AssigmentService assigmentService;


    public StructAnUnivDTO makeStructAnUnivDTO(StructuraAnUniversitar s){
        return new StructAnUnivDTO(s.getPeriodStart(),s.getPeriodEnd(), s.getSchoolPeriodType());
    }


    public List<ScheduleDTO> buildScheduleDTO(AppUser appUser) {

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.findSchedulesForUser(appUser);

        //daca userul nu are nicun schedule
        if (schedules.isEmpty()) {
            scheduleDTOS.add(new ScheduleDTO());
        } else {

            for (Schedule s : schedules) {
                String day = s.getDay();
                String hourStart = s.getHourStart();
                String hourEnd = s.getHourEnd();
                String weekType = s.getWeekType();

                String courseType = scheduleService.findCourseNameAndTypeForSpecificSchedule(s).get(0);
                String courseName = scheduleService.findCourseNameAndTypeForSpecificSchedule(s).get(1);

                scheduleDTOS.add(new ScheduleDTO(day, hourStart, hourEnd, weekType, courseType, courseName));

            }
        }
        return scheduleDTOS;

    }


    public CourseDTO makeCourseDetailsDTO(AppUser appUser, AllCourses currentCourse) {

        String professorLectureString = "";
        String professorLectureEmail = "";
        String professorLabString = "";
        String professorLabEmail = "";
        String professorSemString = "";
        String professorSemEmail = "";
        String classRoomLectureString = "";
        String classRoomLabString = "";
        String classRoomSemString = "";
        CourseLecture currentLecture = null;
        CourseLaboratory currentLab = null;
        CourseSeminary currentSem = null;
        Professor professorLecture = null;
        Professor professorLab = null;
        Professor professorSem = null;
        ClassRoom classRoomLecture = null;
        ClassRoom classRoomLab = null;
        ClassRoom classRoomSem = null;
        String addressLecture = "";
        String addressLab = "";
        String addressSemi = "";
        String obsLecture = "";
        String obsLab = "";
        String obsSem = "";
        if (scheduleService.getUsersLectureForSpecificCourse(appUser, currentCourse).isPresent()) {
            currentLecture = scheduleService.getUsersLectureForSpecificCourse(appUser, currentCourse).get();
            professorLecture = currentLecture.getProfessor();
            professorLectureEmail = optionalToMyString(Optional.ofNullable(professorLecture.getEmail()).toString());
            classRoomLecture = currentLecture.getClassRoom();
            addressLecture = currentLecture.getClassRoom().getAddress();
            obsLecture = currentLecture.getClassRoom().getObservation();

            professorLectureString = professorLecture.toString();
            classRoomLectureString = classRoomLecture.getClassRoomName();
        }
        if (scheduleService.getUsersLaboratoryForSpecificCourse(appUser, currentCourse).isPresent()) {
            currentLab = scheduleService.getUsersLaboratoryForSpecificCourse(appUser, currentCourse).get();
            professorLab = currentLab.getProfessor();
            professorLabEmail = optionalToMyString(Optional.ofNullable(professorLab.getEmail()).toString());
            classRoomLab = currentLab.getClassRoom();
            addressLab = currentLab.getClassRoom().getAddress();
            obsLab = currentLab.getClassRoom().getObservation();

            professorLabString = professorLab.toString();
            classRoomLabString = classRoomLab.getClassRoomName();
        }
        if (scheduleService.getUsersSeminaryForSpecificCourse(appUser, currentCourse).isPresent()) {
            currentSem = scheduleService.getUsersSeminaryForSpecificCourse(appUser, currentCourse).get();
            professorSem = currentSem.getProfessor();
            professorSemEmail = optionalToMyString(Optional.ofNullable(professorSem.getEmail()).toString());
            classRoomSem = currentSem.getClassRoom();
            addressSemi = currentSem.getClassRoom().getAddress();
            obsSem = currentSem.getClassRoom().getObservation();

            professorSemString = professorSem.toString();
            classRoomLabString = classRoomSem.getClassRoomName();
        }

        List<Assigment> coursesAssig = assigmentService.findAssigsForSpecificCourseAndUser(currentCourse, appUser);
        List<AssigmentDTO> assigmentDTOS = new ArrayList<>();
        for (Assigment asi : coursesAssig) {
            assigmentDTOS.add(makeAssigDTO(currentCourse, asi));
        }


        return new CourseDTO(professorLectureString, professorLectureEmail, classRoomLectureString, addressLecture, obsLecture,
                professorLabString, professorLabEmail, classRoomLabString, addressLab, obsLab,
                professorSemString, professorSemEmail, classRoomSemString, addressSemi, obsSem,
                assigmentDTOS);


    }


    public AssigmentDTO makeAssigDTO(AllCourses currentCourse, Assigment assigment) {
       String abreviereFinal = "";
        if(optionalToMyString(Optional.ofNullable(currentCourse.getName()).toString()) != "Not announced" ){
            String[] abreviere = currentCourse.getName().split(" ");
            for (String s: abreviere) {
                abreviereFinal += s.substring(0,1).toUpperCase();
            }
        }

        return new AssigmentDTO(
                assigment.getId().toString(),
                optionalToMyString(Optional.ofNullable(currentCourse.getName()).toString()), abreviereFinal,
                        optionalToMyString(Optional.ofNullable(assigment.getAssigName()).toString()),
                                optionalToMyStringDate(Optional.ofNullable(assigment.getDeadline()).toString()),
                                        optionalToMyString(Optional.ofNullable(assigment.getDescription()).toString()),
                                                optionalToMyString(Optional.ofNullable(assigment.getStatus()).toString())
        );
    }


    public String optionalToMyString(String optional){
        String result = "";
        if(optional.contains("empty")){
            result = "Not announced";
        }else {
            result = optional.substring(9, optional.length()-1);
        }
        return result;
    }



    public String optionalToMyStringDate(String optional){
        String result = "";
        String resultPartial = "";
        if(optional.contains("empty")){
            result = "Not announced";
        }else {
            resultPartial = optional.substring(9, optional.length()-12);
            result = resultPartial.substring(8, 10)+ "-" + resultPartial.substring(5, 7) +"-" + resultPartial.substring(0, 4);
        }
        System.out.println(result);
        return result;
    }

}
