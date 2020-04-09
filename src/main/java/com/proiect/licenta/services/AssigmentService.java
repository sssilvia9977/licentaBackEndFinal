package com.proiect.licenta.services;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Assigment;
import com.proiect.licenta.entities.choices.AssigStatus;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssigmentService {
    private final RepositoryFactory repositoryFactory;

    public Assigment save(Assigment assigment){
        return repositoryFactory.createAssigmentRepository().save(assigment);
    }
    public Optional<Assigment> findAssigmentById(int id){
        return repositoryFactory.createAssigmentRepository().findById(id);
    }
    public List<Assigment> findAllAssigment(){
        return repositoryFactory.createAssigmentRepository().findAll();
    }
    public void deleteAssigment(Assigment assigment){
        repositoryFactory.createAssigmentRepository().delete(assigment);
    }
    public List<Assigment> findAssigsForSpecificCourseAndUser(AllCourses theCourse, AppUser appUser){
        return repositoryFactory.createAssigmentRepository().findByAllCoursesAndAppUser(theCourse, appUser);
    }

    public List<Assigment> findAssigByUser(AppUser appUser){
        return repositoryFactory.createAssigmentRepository().findAllByAppUser(appUser);
    }


    public void setStatus(Assigment assigment){
       String compare = AssigStatus.COMPLETED;
        if (assigment.getStatus().equals(compare)) {
            assigment.setStatus(AssigStatus.NOTCompleted);
        }else{
            assigment.setStatus(AssigStatus.COMPLETED);
        }
        save(assigment);
    }

}
