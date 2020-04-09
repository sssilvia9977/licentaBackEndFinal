package com.proiect.licenta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity(name = "faculty")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facultyId;

    @ManyToOne
    private University university;

    @OneToMany(mappedBy = "faculty")
    private List<AllCourses> allCourses;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<ClassRoom> classRoom;

    private String fullName;
    private String abbreviation;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<AppUser> appUser;

    public Faculty(University university1, String fullName, String abbreviation) {
        university = university1;
        this.fullName = fullName;
        this.abbreviation = abbreviation;
    }


    public String toString() {
        return fullName;
    }
}
