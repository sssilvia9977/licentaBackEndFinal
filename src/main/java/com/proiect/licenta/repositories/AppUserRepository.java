package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Recommendation;
import com.proiect.licenta.entities.Schedule;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends Repository<AppUser, Integer> {

    AppUser save(AppUser appUser);
    List<AppUser> findAll();
    Optional<AppUser> findByAppUserId(int id);
    Optional<AppUser> findByUsername(String username);
    void delete(AppUser appUser);

}
