package com.proiect.licenta.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionID {

    private String sessionId;
    private String username;
    private String password;

    private String courseName;

    private String assigId;
    private String assigTitle;
    private String assigDeadline;
    private String assigDescription;


}
