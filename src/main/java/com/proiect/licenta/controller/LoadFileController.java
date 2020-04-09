package com.proiect.licenta.controller;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class LoadFileController {

    private AppUser currentUser;

    private final ClassRoomService classRoomService;
    private final AppUserServices appUserServices;
    private final ScheduleService scheduleService;
    private final StructuraAnUniverService structuraAnUniverService;

    @PostMapping(value= "/sendSessionID")
    public void currentUserId(@RequestBody SessionID sessionID){
        String idIntermediat = sessionID.getSessionId().substring(1, sessionID.getSessionId().length()-1);
        int id = Integer.parseInt(idIntermediat);
        if( appUserServices.findAppUserById(id).isPresent()){
            currentUser = appUserServices.findAppUserById(id).get();
        }
    }

    @PostMapping(value = "/nameExcel")
    public void excel(@RequestBody String name){
        System.out.println(name.toString());
    }

    @PostMapping(value = "/importExcel")
    public void mapExcelToDb(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());

        XSSFSheet orarSheet = null;
        XSSFSheet saliSheet = null;
        XSSFSheet structSheet = null ;

        for(XSSFSheet sheet : workbook) {
            if (sheet.getSheetName().contains("sal")) {
                saliSheet = sheet;
            }
            if (sheet.getSheetName().contains("struct")) {
                structSheet = sheet;
            }
            if (sheet.getSheetName().contains("orar") || sheet.getSheetName().contains("schedule")) {
                orarSheet = sheet;
            }

        }

        classRoomService.saveFromExcel(saliSheet, currentUser);
        structuraAnUniverService.saveFromExcel(structSheet, currentUser);
        scheduleService.saveFromExcel(orarSheet, currentUser);


    }




}
