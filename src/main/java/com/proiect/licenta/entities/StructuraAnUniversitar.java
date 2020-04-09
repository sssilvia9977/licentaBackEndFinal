package com.proiect.licenta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class StructuraAnUniversitar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date periodStart;
    private Date periodEnd;
    private String schoolPeriodType;

    @OneToOne
    private University university;

    public StructuraAnUniversitar(Date periodStart, Date periodEnd, String schoolPeriodType, University university){
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.schoolPeriodType = schoolPeriodType;
        this.university = university;
    }


    @Override
    public String toString() {
        return "StructuraAnUniversitar{" +
                "periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", schoolPeriodType='" + schoolPeriodType + '\'' +
                '}';
    }
}
