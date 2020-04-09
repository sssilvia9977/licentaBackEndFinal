package com.proiect.licenta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "university")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uniId;
    private String fullName;
    private String abbreviation;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Faculty> faculties;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<AppUser> appUser;


    public University( String numeUni, String abbreviation) {
        this.fullName = numeUni;
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return  fullName;
    }
}
