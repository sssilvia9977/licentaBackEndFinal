package com.proiect.licenta.dto;

import com.proiect.licenta.entities.AllCourses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private int yearOfStudy;

    private String facultyName;
    private String universityName;

    private List<String> allCoursesCurrentUser;



}
