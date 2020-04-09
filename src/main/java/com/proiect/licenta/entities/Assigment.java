package com.proiect.licenta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Assigment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date deadline;
    private String assigName;
    private String description;
    private String status;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private AllCourses allCourses;

    @Override
    public String toString() {
        return "Assigment{" +
                "deadline=" + deadline +
                ", assigName='" + assigName + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
