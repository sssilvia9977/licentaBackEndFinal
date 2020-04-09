package com.proiect.licenta.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String day;
    private String hourStart;
    private String hourEnd;
    private String weekType;

    @ManyToOne
    @JoinColumn
    private CourseLaboratory courseLaboratory;

    @ManyToOne
    @JoinColumn
    private CourseLecture courseLecture;

    @ManyToOne
    @JoinColumn
    private CourseSeminary courseSeminary;

    @ManyToOne
    @JoinColumn
    private AppUser appUser;

    public Schedule(String day, String hourStart, String hourEnd, String weekType, CourseSeminary courseSeminary, AppUser appUser){
        this.day = day;
        this.hourEnd = hourEnd;
        this.hourStart = hourStart;
        this.weekType = weekType;
        this.courseSeminary = courseSeminary;
        this.appUser = appUser;
    }
    public Schedule( String day, String hourStart, String hourEnd, String weekType, CourseLaboratory courseLaboratory, AppUser appUser){
        this.day = day;
        this.hourEnd = hourEnd;
        this.hourStart = hourStart;
        this.weekType = weekType;
        this.courseLaboratory = courseLaboratory;
        this.appUser = appUser;
    }
    public Schedule( String day, String hourStart, String hourEnd, String weekType, CourseLecture courseLecture, AppUser appUser){
        this.day = day;
        this.hourEnd = hourEnd;
        this.hourStart = hourStart;
        this.weekType = weekType;
        this.courseLecture = courseLecture;
        this.appUser = appUser;
    }


    @Override
    public String toString() {
        return "Schedule :" + day  +
                ", hourStart='" + hourStart +
                ", hourEnd='" + hourEnd +
                ", weekType='" + weekType ;
    }
}
