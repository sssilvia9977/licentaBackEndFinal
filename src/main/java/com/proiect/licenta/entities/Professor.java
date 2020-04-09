package com.proiect.licenta.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Professor{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<CourseLecture> courseLectures;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<CourseSeminary> courseSeminaries;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<CourseLaboratory> classLaboratories;

    public Professor(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
