package com.proiect.licenta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "recommendation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recId;
    private String numeleLocal;
    private String address;
    private String initialComment;
    private String category;

    @ManyToOne
    private AppUser appUser;






}
