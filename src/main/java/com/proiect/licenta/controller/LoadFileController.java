package com.proiect.licenta.controller;

import com.proiect.licenta.entities.AppUser;
import com.proiect.licenta.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
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
        int id = sessionID.getSessionId();
        if( appUserServices.findAppUserById(id).isPresent()){
            currentUser = appUserServices.findAppUserById(id).get();
        }
    }

    @PostMapping(value = "/nameExcel")
    public void excel(@RequestBody String name){
        System.out.println(name.toString());
    }

    @PostMapping(value = "/importExcel")
    public String mapExcelToDb(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {

        String statusMessage = "Your schedule has been uploaded!";
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());

        Sheet orarSheet = null;
        Sheet saliSheet = null;
        Sheet structSheet = null ;

        for(Sheet sheet : workbook) {
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

        return statusMessage;

    }




}
