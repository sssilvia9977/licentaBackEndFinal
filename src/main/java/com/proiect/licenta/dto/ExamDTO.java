package com.proiect.licenta.dto;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Exam;


public class ExamDTO {

    private int id;
    private String date;
    private String details;
    private String courseAbreviere;
    private String courseName;

    public ExamDTO(Exam exam){
        this.id = exam.getId();
        this.date = exam.getDate().toString();
        this.details  = exam.getDetails();
        this.courseAbreviere = abreviere(exam.getAllCourses().getName());
        this.courseName = exam.getAllCourses().getName();
    }


    public String abreviere(String a){
        String[] abreviere = a.split(" ");
        String abreviereFinal = "";
        for (String s: abreviere) {
            abreviereFinal += s.substring(0,1).toUpperCase();
        }
        return abreviereFinal;
    }


}
