package com.proiect.licenta.controller;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.services.AppUserServices;
import com.proiect.licenta.services.SessionID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class LoginController {

    private final AppUserServices appUserServices;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/")
    public SessionID login(@RequestBody SessionID credentials){
        SessionID sessionId = new SessionID();

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        if(appUserServices.findUserByUsername(username).isPresent()){
            AppUser appUser = appUserServices.findUserByUsername(username).get();

            if(passwordEncoder.matches(password, appUser.getPassword())){
                sessionId.setSessionId(appUser.getAppUserId());
            }

        }
        return sessionId;
    }



}
