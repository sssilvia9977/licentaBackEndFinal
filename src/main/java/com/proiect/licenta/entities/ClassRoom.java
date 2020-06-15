package com.proiect.licenta.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String classRoomName;
    private String address;
    private String observation;


    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<CourseLecture> courseLectures;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<CourseLaboratory> courseLaboratories;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<CourseSeminary> courseSeminaries;

    public ClassRoom(String classRoomName, String address, String observation){
        this.classRoomName = classRoomName;
        this.address = address;
        this.observation = observation;
    }

    public ClassRoom(String classRoomName, String address){
        this.classRoomName = classRoomName;
        this.address = address;
    }

    public ClassRoom(String classRoomName){
        this.classRoomName = classRoomName;
    }


    @Override
    public String toString() {
        return "ClassRoom{" +
                "classRoomName='" + classRoomName + '\'' +
                ", address='" + address + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}
