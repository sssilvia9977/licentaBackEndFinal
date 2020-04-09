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
    private Faculty faculty;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<CourseLecture> courseLectures;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<CourseLaboratory> courseLaboratories;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<CourseSeminary> courseSeminaries;

    @OneToMany(mappedBy = "allCourses", cascade = CascadeType.ALL)
    private List<Assigment> assigment;


    public AllCourses(String name, Faculty faculty){
        this.name = name;
        this.faculty  = faculty;
    }

    public String toString(){
        return name;
    }

}
