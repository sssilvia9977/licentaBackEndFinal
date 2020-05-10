package com.proiect.licenta;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Assigment;
import com.proiect.licenta.entities.Faculty;
import com.proiect.licenta.entities.University;
import com.proiect.licenta.entities.choices.AssigStatus;
import com.proiect.licenta.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class Seed implements CommandLineRunner {

    private final AppUserServices appUserServices;
    private final FacultyService facultyService;
    private final UniversityService universityService;
    private final AssigmentService assigmentService;
    private final ClassRoomService classRoomService;

    @Override
    public void run(String... args) {
/*
        University university1 = new University("Universitatea Tehnica din Cluj-Napoca", "UTCN");
        universityService.save(university1);

        Faculty faculty1 = new Faculty(university1, "Facultatea de automatica si calculatoare", "AC");
        facultyService.save(faculty1);

        Faculty faculty2 = new Faculty(university1, "Facultatea de constructii", "C");
        facultyService.save(faculty2);


        AppUser appUser = new AppUser(null, "Calin", "File de poveste", "print@ex.com", "a", "a", 3, university1, faculty2,null,null,null, null);
        appUserServices.save(appUser);
        Assigment assigmentForUser1 = new Assigment(null, null, "Learn threads", "Do your beste to learn threads",
                AssigStatus.NOTCompleted, appUser, null);
        assigmentService.save(assigmentForUser1);

        AppUser appUser2 = new AppUser(null, "Catalina", "Muritoare", "rudemari@ex.com", "fata", "parinti", 3, university1, faculty1,null, null, null,null);
        appUserServices.save(appUser2);

        AppUser appUser3 = new AppUser(null, "Luceafarul", "Nemuriorul", "rece@ex.com", "dorinta", "igata", 3, university1, faculty2, null,null,null, null);
        appUserServices.save(appUser3);

        List<AppUser> apps = appUserServices.findAllAppUsers();
        Optional<AppUser> a = appUserServices.findAppUserById(2);

        List<University> uni = universityService.findAllUniversities();
        Optional<University> u = universityService.findUniversityById(1);

        List<Faculty> fac = facultyService.findAllFaculties();
        Optional<Faculty> f = facultyService.findFacultyById(1);

*/

    }

}
