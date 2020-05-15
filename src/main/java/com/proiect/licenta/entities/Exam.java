package com.proiect.licenta.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;


@Entity(name = "exam")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Exam {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private String details;

    @ManyToOne
    private AllCourses allCourses;

    @ManyToOne
    private AppUser appUser;




}
