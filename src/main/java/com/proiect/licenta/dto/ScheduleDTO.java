package com.proiect.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private String day;
    private String hourStart;
    private String hourEnd;
    private String weekType;
    private String courseType;
    private String courseName;
    private String courseAbreviere;

    private String courseClassRoom;
    private String courseAddress;
    private String courseAddressDetails;


}
