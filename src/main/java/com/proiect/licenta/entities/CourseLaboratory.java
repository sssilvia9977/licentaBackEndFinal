
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
public class CourseLaboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "courseLaboratory", cascade = CascadeType.ALL)
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


    public CourseLaboratory(AllCourses allCourses, Professor professor, ClassRoom classRoom){
        this.allCourses = allCourses;
        this.professor = professor;
        this.classRoom = classRoom;
    }

    public String toString(){
        return allCourses.getName() + " " + professor.getName();
    }


}
