package com.proiect.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssigmentDTO {

    private String id;
    private String courseName;
    private String courseAbreviere;
    private String title;
    private String dateLine;
    private String description;
    private String status;


}
