package com.proiect.licenta.services;

import com.proiect.licenta.entities.*;
import com.proiect.licenta.entities.choices.CourseSmallScheduleType;
import com.proiect.licenta.entities.choices.WeekType;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final RepositoryFactory repositoryFactory;

    private final AllCoursesService allCoursesService;
    private final ProfessorService professorService;
    private final ClassRoomService classRoomService;
    private final CourseLaboratoryService laboratoryService;
    private final CourseSeminaryService seminaryService;
    private final CourseLectureService lectureService;


    public Schedule save(Schedule schedule) {
        return repositoryFactory.createScheduleRepository().save(schedule);
    }

    public Optional<Schedule> findScheduleById(int id) {
        return repositoryFactory.createScheduleRepository().findById(id);
    }

    public List<Schedule> findAllSchedules() {
        return repositoryFactory.createScheduleRepository().findAll();
    }

    public void deleteSchedule(Schedule schedule) {
        repositoryFactory.createScheduleRepository().delete(schedule);
    }

    public List<Schedule> findSchedulesForUser(AppUser appUser) {
        return repositoryFactory.createScheduleRepository().findByAppUser(appUser);
    }

    public List<String> findCourseNameAndTypeForSpecificSchedule(Schedule s){
        List<String> result = new ArrayList<>();
        String courseType = "";
        String courseName = "";
        if (Optional.ofNullable(s.getCourseLecture()).isPresent()) {
            courseType = CourseSmallScheduleType.LECTURE;
            courseName = s.getCourseLecture().getAllCourses().getName();
        }
        if (Optional.ofNullable(s.getCourseLaboratory()).isPresent()) {
            courseType = CourseSmallScheduleType.LABORATORY;
            courseName = s.getCourseLaboratory().getAllCourses().getName();
        }
        if (Optional.ofNullable(s.getCourseSeminary()).isPresent()) {
            courseType = CourseSmallScheduleType.SEMINARY;
            courseName = s.getCourseSeminary().getAllCourses().getName();
        }

        result.add(courseType);
        result.add(courseName);
        return result;

    }



    public Optional<CourseLecture> getUsersLectureForSpecificCourse(AppUser appUser, AllCourses theCourse) {
        List<Schedule> schedules = findSchedulesForUser(appUser);
        for (Schedule s : schedules) {
            if (Optional.ofNullable(s.getCourseLecture()).isPresent()) {
                if (s.getCourseLecture().getAllCourses() == theCourse) {
                    return Optional.ofNullable(s.getCourseLecture());
                }
            }

        }
        return Optional.empty();
    }

    public Optional<CourseLaboratory> getUsersLaboratoryForSpecificCourse(AppUser appUser, AllCourses theCourse) {
        List<Schedule> schedules = findSchedulesForUser(appUser);
        for (Schedule s : schedules) {
            if (Optional.ofNullable(s.getCourseLaboratory()).isPresent()) {
                if (s.getCourseLaboratory().getAllCourses() == theCourse) {
                    return Optional.ofNullable(s.getCourseLaboratory());
                }
            }
        }
        return Optional.empty();
    }

    public Optional<CourseSeminary> getUsersSeminaryForSpecificCourse(AppUser appUser, AllCourses theCourse) {
        List<Schedule> schedules = findSchedulesForUser(appUser);
        for (Schedule s : schedules) {
            if (Optional.ofNullable(s.getCourseSeminary()).isPresent()) {
                if (s.getCourseSeminary().getAllCourses() == theCourse) {
                    return Optional.ofNullable(s.getCourseSeminary());
                }
            }
        }
        return Optional.empty();
    }

    public void saveFromExcel(Sheet sheet, AppUser currentUser) {

        allCoursesService.findAllAllCoursesByUser(currentUser).forEach(allCoursesService::deleteAllCourse);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // skip the header row
        while (rowIterator.hasNext()) {

            Schedule schedule;
            Row nextRow = rowIterator.next();

            Iterator<Cell> cellIterator = nextRow.cellIterator();

            String day = cellIterator.next().getStringCellValue();
            String hourStart = cellIterator.next().getStringCellValue();
            String hourEnd = cellIterator.next().getStringCellValue();

            String courseNameString = cellIterator.next().getStringCellValue();
            String courseTypeString = cellIterator.next().toString();
            String professorString = cellIterator.next().getStringCellValue();
            String classRoomString = cellIterator.next().toString();

            String weekOdd = WeekType.ALWAYS;
            if (cellIterator.hasNext()) {
                if (cellIterator.next().toString().equals("odd"))
                    weekOdd = WeekType.ODD;
                else
                    weekOdd = WeekType.EVEN;
            }

            try {
                ClassRoom classRoom;
                Optional<ClassRoom> classRoomOptional = classRoomService.findClassRoomByName(classRoomString);
                classRoom = classRoomOptional.orElseGet(ClassRoom::new);
                    /*
                    TODO: salveaza classul demai sus
                     */
                AllCourses aCourses;
                if (allCoursesService.findACourseByName(courseNameString).isEmpty()) {
                    aCourses = new AllCourses(courseNameString, currentUser);
                    allCoursesService.save(aCourses);
                } else
                    aCourses = allCoursesService.findACourseByName(courseNameString).get();


                Professor professor;
                if (professorService.findProfessorByName(professorString).isEmpty()) {
                    professor = new Professor(professorString);
                    professorService.save(professor);
                } else
                    professor = professorService.findProfessorByName(professorString).get();


                int lectureID;
                int laboratoryID;
                int seminaryID;
                switch (courseTypeString) {
                    case "0.0": //lecture
                        if (lectureService.findCourseLectureById(aCourses.getId()).isPresent()
                                && lectureService.findCourseLectureById(aCourses.getId()).get().getClassRoom().getClassRoomName().equals(classRoomString)
                                && lectureService.findCourseLectureById(aCourses.getId()).get().getProfessor().getName().equals(professorString)
                        ) {
                            lectureID = lectureService.findCourseLectureById(aCourses.getId()).get().getId();
                        } else {
                            CourseLecture courseLecture = new CourseLecture(aCourses, professor, classRoom);
                            lectureService.save(courseLecture);
                            lectureID = courseLecture.getId();
                        }
                        schedule = new Schedule(day, hourStart, hourEnd, weekOdd, lectureService.findCourseLectureById(lectureID).get(), currentUser);
                        save(schedule);
                        break;

                    case "1.0": //laboratory
                        if (laboratoryService.findCourseLaboratoryById(aCourses.getId()).isPresent()
                                && laboratoryService.findCourseLaboratoryById(aCourses.getId()).get().getClassRoom().getClassRoomName().equals(classRoomString)
                                && laboratoryService.findCourseLaboratoryById(aCourses.getId()).get().getProfessor().getName().equals(professorString)
                        ) {
                            laboratoryID = laboratoryService.findCourseLaboratoryById(aCourses.getId()).get().getId();
                        } else {
                            CourseLaboratory courseLaboratory = new CourseLaboratory(aCourses, professor, classRoom);
                            laboratoryService.save(courseLaboratory);
                            laboratoryID = courseLaboratory.getId();
                        }
                        schedule = new Schedule(day, hourStart, hourEnd, weekOdd, laboratoryService.findCourseLaboratoryById(laboratoryID).get(), currentUser);
                        save(schedule);
                        break;

                    case "2.0": //seminary
                        if (seminaryService.findCourseSeminaryById(aCourses.getId()).isPresent()
                                && seminaryService.findCourseSeminaryById(aCourses.getId()).get().getClassRoom().getClassRoomName().equals(classRoomString)
                                && seminaryService.findCourseSeminaryById(aCourses.getId()).get().getProfessor().getName().equals(professorString)
                        ) {
                            seminaryID = seminaryService.findCourseSeminaryById(aCourses.getId()).get().getId();
                        } else {
                            CourseSeminary courseSeminary = new CourseSeminary(aCourses, professor, classRoom);
                            seminaryService.save(courseSeminary);
                            seminaryID = courseSeminary.getId();
                        }
                        schedule = new Schedule(day, hourStart, hourEnd, weekOdd, seminaryService.findCourseSeminaryById(seminaryID).get(), currentUser);
                        save(schedule);
                        break;
                }
            } catch (Exception e) {
                System.out.println(day + " " + hourStart + " " + hourEnd + " " + courseNameString + " " + courseTypeString + " " + professorString + " " + classRoomString + " " + weekOdd);
            }


        }

    }

}
