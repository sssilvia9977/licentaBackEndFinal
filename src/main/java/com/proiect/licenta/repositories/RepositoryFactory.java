package com.proiect.licenta.repositories;

public interface RepositoryFactory {

    AppUserRepository createAppUserRepository();
    FacultyRepository createFacultyRepository();
    UniversityRepository createUniversityRepository();
    ClassRoomRepository createClassRoomRepository();
    StructuraAnUniversitarRepository createStructuraAnUniversitarRepository();
    ScheduleRepository createScheduleRepository();
    AllCoursesRepository createAllCoursesRepository();
    AssigmentRepository createAssigmentRepository();
    CourseLaboratoryRepository createCourseLaboratoryRepository();
    CourseLectureRepository createCourseLectureRepository();
    CourseSeminaryRepository createCourseSeminaryRepository();
    ProfessorRepository createProfessorRepository();
    RecommendationRepository createRecommendationRepository();

}
