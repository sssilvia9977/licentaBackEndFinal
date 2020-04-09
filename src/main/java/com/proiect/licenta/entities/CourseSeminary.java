
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
public class CourseSeminary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "courseSeminary", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    @ManyToOne
    @JoinColumn
    private AllCourses allCourses;

    @ManyToOne
    @JoinColumn
    private Professor professor;

    @ManyToOne
    @JoinColumn
    private ClassRoom classRoom;


    public CourseSeminary(AllCourses allCourses, Professor professor, ClassRoom classRoom){
        this.allCourses = allCourses;
        this.professor = professor;
        this.classRoom = classRoom;
    }

    public String toString(){
        return allCourses.getName() + " " + professor.getName();
    }



}
