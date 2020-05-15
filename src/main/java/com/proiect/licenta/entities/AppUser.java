package com.proiect.licenta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private int yearOfStudy;

    private String university;
    private String faculty;

    @OneToMany(mappedBy = "appUser")
    private List<AllCourses> allCourses;


    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Schedule> schedule;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Assigment> assignments;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Exam> exams;




    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
