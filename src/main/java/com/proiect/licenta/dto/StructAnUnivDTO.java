package com.proiect.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructAnUnivDTO {

    private Date periodStart;
    private Date periodEnd;
    private String schoolPeriodType;


}
