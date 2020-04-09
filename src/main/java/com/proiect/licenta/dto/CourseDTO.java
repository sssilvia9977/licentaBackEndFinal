package com.proiect.licenta.dto;

import com.proiect.licenta.entities.Assigment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private String name;
    private String abreviere;

    private String professorLecture;
    private String professorLectureEmail;
    private String classRoomLecture;
    private String addressLecture;
    private String observationLecture;

    private String professorLab;
    private String professorLabEmail;
    private String classRoomLab;
    private String addressLab;
    private String observationLab;

    private String professorSeminary;
    private String professorSeminaryEmail;
    private String classRoomSeminary;
    private String addressSeminary;
    private String observationSeminary;

    private List<AssigmentDTO> assigmentDTOS;

    public CourseDTO(String name, String abreviere) {
        this.abreviere = abreviere;
        this.name = name;
    }

    public CourseDTO(String professorLecture,
                     String professorLectureEmail,
                     String classRoomLecture,
                     String addressLecture,
                     String observationLecture,

                     String professorLab,
                     String professorLabEmail,
                     String classRoomLab,
                     String addressLab,
                     String observationLab,

                     String professorSeminary,
                     String professorSeminaryEmail,
                     String classRoomSeminary,
                     String addressSeminary,
                     String observationSeminary,
                     List<AssigmentDTO> assigmentDTOS) {
        this.professorLecture = professorLecture;
        this.classRoomLecture = classRoomLecture;
        this.addressLecture = addressLecture;
        this.observationLecture = observationLecture;
        this.professorLab = professorLab;
        this.classRoomLab = classRoomLab;
        this.addressLab = addressLab;
        this.observationLab = observationLab;

        this.professorLabEmail = professorLabEmail;
        this.professorLectureEmail = professorLectureEmail;
        this.professorSeminaryEmail = professorSeminaryEmail;


        this.professorSeminary = professorSeminary;
        this.classRoomSeminary = classRoomSeminary;
        this.addressSeminary = addressSeminary;
        this.observationSeminary = observationSeminary;

        this.assigmentDTOS = assigmentDTOS;
    }


}
