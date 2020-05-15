package com.proiect.licenta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aop.aspectj.annotation.LazySingletonAspectInstanceFactoryDecorator;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class AllCourses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private AppUser appUser;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<CourseLecture> courseLectures;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<CourseLaboratory> courseLaboratories;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<CourseSeminary> courseSeminaries;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<Assigment> assigment;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<Exam> exams ;


    public AllCourses(String name, AppUser appUser){
        this.name = name;
        this.appUser  = appUser;
    }

    public String toString(){
        return name;
    }

}
