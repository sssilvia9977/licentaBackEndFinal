package com.proiect.licenta.config;

import com.proiect.licenta.services.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/addUser", "/sendSessionID", "/nameExcel", "/importExcel", "/checkUniq",  "/getAllRec", "/getRecPostedByUser",
                "/addRec", "/deleteRec", "/getAllCategories",  "/getRecEAT_CHEAP", "/getRecDISCO", "/getRecCONCERT", "/getRecCOFFEE_STUDY", "/getRecCOFFEE_TOGO", "/getRecRESTAURANT",
                "/getRecSPLURGE", "/getUsername", "/updateUser","/getCourses","/getCourseDetails", "/getSchedule", "/getStructAnUniv", "/setAssigStatus",
                "/addAssig", "/deleteAssig", "/updateAssig", "/getAllAssigs", "/getExam", "/addExam", "/getExamForCourse", "/deleteExam"
        ).permitAll()
                .anyRequest().authenticated().and()//.formLogin().loginPage("/").permitAll();
                .httpBasic().and()
                // next line disables session creation (forces true HTTP Basic behavior)
                .cors().and()
                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
