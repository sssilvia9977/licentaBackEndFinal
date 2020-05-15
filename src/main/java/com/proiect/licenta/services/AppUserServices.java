package com.proiect.licenta.services;

import com.proiect.licenta.entities.AllCourses;
import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServices {

    private final RepositoryFactory repositoryFactory;
    private final AllCoursesService allCoursesService;

    public AppUser save(AppUser appUser){
        return repositoryFactory.createAppUserRepository().save(appUser);
    }
    public Optional<AppUser> findAppUserById(int id){
        return repositoryFactory.createAppUserRepository().findByAppUserId(id);
    }
    public Optional<AppUser> findUserByUsername(String username){
        return repositoryFactory.createAppUserRepository().findByUsername(username);
    }
    public List<AppUser> findAllAppUsers(){
        return repositoryFactory.createAppUserRepository().findAll();
    }
    public void deleteAppUser(AppUser appUser){
        repositoryFactory.createAppUserRepository().delete(appUser);
    }

    public boolean checkUniqueUsername(String username){
        return findAllAppUsers().stream().noneMatch(e -> e.getUsername().equals(username));
    }



    public List<AllCourses> findAllCoursesByCurrentUser(AppUser appUser){
        List<AllCourses> allCoursesList = allCoursesService.findAllAllCoursesByUser(appUser);
        return allCoursesList;
    }



}
