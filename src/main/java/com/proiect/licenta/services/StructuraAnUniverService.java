package com.proiect.licenta.services;


import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.entities.Faculty;
import com.proiect.licenta.entities.StructuraAnUniversitar;
import com.proiect.licenta.entities.University;
import com.proiect.licenta.entities.choices.SchoolPeriodType;
import com.proiect.licenta.repositories.RepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StructuraAnUniverService {

    private final RepositoryFactory repositoryFactory;
    private final UniversityService universityService;

    public StructuraAnUniversitar save(StructuraAnUniversitar structuraAnUniversitar){
        return repositoryFactory.createStructuraAnUniversitarRepository().save(structuraAnUniversitar);
    }
    public Optional<StructuraAnUniversitar> findStructuraAnUniversitarById(int id){
        return repositoryFactory.createStructuraAnUniversitarRepository().findById(id);
    }
    public List<StructuraAnUniversitar> findAllStructuraAnUniversitar(){
        return repositoryFactory.createStructuraAnUniversitarRepository().findAll();
    }
    public void deleteStructuraAnUniversitar(StructuraAnUniversitar structuraAnUniversitar){
        repositoryFactory.createStructuraAnUniversitarRepository().delete(structuraAnUniversitar);
    }

    public List<StructuraAnUniversitar> findStructuraByUniversity(University university){
       return repositoryFactory.createStructuraAnUniversitarRepository().findAllByUniversity(university);
    }


    public void saveFromExcel(XSSFSheet sheet, AppUser currentUSer){
        /*
        TODO: aici trebuie sa salvez universitatea studentului (sa o iau de fapt din tabela de uni)
        pentru moment, am sa pun prima uni care e in baza de date
         */

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // skip the header row
        while(rowIterator.hasNext()){
            StructuraAnUniversitar structuraAnUniversitar;
            Row nextRow = rowIterator.next();

            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Date periodStart = cellIterator.next().getDateCellValue();
            Date periodEnd = cellIterator.next().getDateCellValue();
            Cell periodType = cellIterator.next();

            String periodTypeString = "";
            if(periodType.toString().contains("olid"))
                periodTypeString = SchoolPeriodType.HOLIDAY;
            else if(periodType.toString().contains("chool"))
                periodTypeString = SchoolPeriodType.SCHOOL;
            else if(periodType.toString().contains("xams"))
                periodTypeString = SchoolPeriodType.EXAM;
            else periodTypeString = SchoolPeriodType.SCHOOL;

            structuraAnUniversitar = new StructuraAnUniversitar(periodStart, periodEnd, periodTypeString ,
                    currentUSer.getUniversity());
            save(structuraAnUniversitar);
        }


    }


}
