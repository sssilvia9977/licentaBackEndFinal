package com.proiect.licenta.services;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final RepositoryFactory repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //specify concrete by using factory
        AppUser appUser = repository.createAppUserRepository().findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown username!"));
        return new User(appUser.getUsername(), appUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("Role_AppUser")));

    }


}
