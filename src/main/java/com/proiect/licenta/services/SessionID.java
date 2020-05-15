package com.proiect.licenta.services;

import com.proiect.licenta.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionID {

    private int sessionId;
    private String username;
    private String password;
    private String email;
    private String faculty;
    private String uni;
    private String fname;
    private String lname;

    private String courseName;

    private String assigId;
    private String assigTitle;
    private String assigDeadline;
    private String assigDescription;

    private int scheduleDay;
    private String scheduleDate;

    private int recId;
    private String placeName;
    private String address;
    private String initialComment;
    private String category;

    private int examId;
    private String examDate;
    private String examDetails;
    private String course;



}
