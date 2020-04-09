package com.proiect.licenta.services;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.ClassRoom;
import com.proiect.licenta.entities.Faculty;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassRoomService {

    private final RepositoryFactory repositoryFactory;
    private final FacultyService facultyService;

    public ClassRoom save(ClassRoom classRoom){
        return repositoryFactory.createClassRoomRepository().save(classRoom);
    }
    public Optional<ClassRoom> findClassRoomById(int id){
        return repositoryFactory.createClassRoomRepository().findById(id);
    }
    public List<ClassRoom> findAllClassRooms(){
        return repositoryFactory.createClassRoomRepository().findAll();
    }
    public void deleteClassRoom(ClassRoom classRoom){
        repositoryFactory.createClassRoomRepository().delete(classRoom);
    }

    public Optional<ClassRoom> findClassRoomByName(String name){
        return repositoryFactory.createClassRoomRepository().findByClassRoomName(name);
    }

    public void saveFromExcel(XSSFSheet sheet, AppUser currentUser){
        System.out.println(facultyService.findFacultyById(currentUser.getFaculty().getFacultyId()).get().toString());
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // skip the header row
        while (rowIterator.hasNext()) {
            ClassRoom classRoom;
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            Cell classRoomName = cellIterator.next();
            Cell address = cellIterator.next();
            if (cellIterator.hasNext()) {
                Cell observations = cellIterator.next();
                classRoom = new ClassRoom(classRoomName.toString(), address.toString(), observations.toString(),
                        facultyService.findFacultyById(currentUser.getFaculty().getFacultyId()).get());
            } else {
                classRoom = new ClassRoom(classRoomName.toString(), address.toString(),
                        facultyService.findFacultyById(currentUser.getFaculty().getFacultyId()).get());
            }
            save(classRoom);
        }
    }


}
