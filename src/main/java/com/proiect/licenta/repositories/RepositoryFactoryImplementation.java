package com.proiect.licenta.repositories;

import com.proiect.licenta.entities.CourseLecture;
import com.proiect.licenta.entities.CourseSeminary;
import com.proiect.licenta.entities.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RepositoryFactoryImplementation implements RepositoryFactory {

    private final AppUserRepository appUserRepository;
    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;
    private final ClassRoomRepository classRoomRepository;
    private final StructuraAnUniversitarRepository structuraAnUniversitarRepository;
    private final ScheduleRepository scheduleRepository;
    private final AssigmentRepository assigmentRepository;
    private final AllCoursesRepository allCoursesRepository;
    private final CourseLectureRepository courseLectureRepository;
    private final CourseLaboratoryRepository courseLaboratoryRepository;
    private final CourseSeminaryRepository courseSeminaryRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public AppUserRepository createAppUserRepository() {
        return appUserRepository;
    }

    @Override
    public FacultyRepository createFacultyRepository() {
        return facultyRepository;
    }

    @Override
    public UniversityRepository createUniversityRepository() {
        return universityRepository;
    }

    @Override
    public ClassRoomRepository createClassRoomRepository() {
        return classRoomRepository;
    }

    @Override
    public StructuraAnUniversitarRepository createStructuraAnUniversitarRepository() {
        return structuraAnUniversitarRepository;
    }

    @Override
    public ScheduleRepository createScheduleRepository() {
        return scheduleRepository;
    }

    @Override
    public AllCoursesRepository createAllCoursesRepository() {
        return allCoursesRepository;
    }

    @Override
    public AssigmentRepository createAssigmentRepository() {
        return assigmentRepository;
    }

    @Override
    public CourseLaboratoryRepository createCourseLaboratoryRepository() {
        return courseLaboratoryRepository;
    }

    @Override
    public CourseLectureRepository createCourseLectureRepository() {
        return courseLectureRepository;
    }

    @Override
    public CourseSeminaryRepository createCourseSeminaryRepository() {
        return courseSeminaryRepository;
    }

    @Override
    public ProfessorRepository createProfessorRepository() {
        return professorRepository;
    }
}
