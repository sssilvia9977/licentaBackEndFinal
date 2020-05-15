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
    private AppUser appUser;

    public StructuraAnUniversitar(Date periodStart, Date periodEnd, String schoolPeriodType, AppUser appUser){
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.schoolPeriodType = schoolPeriodType;
        this.appUser = appUser;
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
