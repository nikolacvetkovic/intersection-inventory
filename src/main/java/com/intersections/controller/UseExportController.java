package com.intersections.controller;

import com.intersections.dao.IntersectionDao;
import com.intersections.dao.PedestrianPushButtonDao;
import com.intersections.dao.AccessDao;
import com.intersections.dao.TrafficSignalControllerDao;
import com.intersections.dao.PoleDao;
import com.intersections.dao.DetectorDao;
import com.intersections.dao.PedestrianDisplayDao;
import com.intersections.dao.SignalHeadDao;
import com.intersections.model.*;
import com.intersections.excelservice.ExportExcel;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "use")
public class UseExportController {
    
    @Autowired
    IntersectionDao intersectionDao;
    @Autowired
    TrafficSignalControllerDao trafficSignalControllerDao;
    @Autowired
    AccessDao accessDao;
    @Autowired
    DetectorDao detectorDao;
    @Autowired
    PoleDao poleDao;
    @Autowired
    SignalHeadDao signalHeadDao;
    @Autowired
    PedestrianPushButtonDao pedestrianPushButtonDao;
    @Autowired
    PedestrianDisplayDao pedestrianDisplayDao;
    @Autowired
    ExportExcel exportExcel;
    
    @RequestMapping(value = "/search")
    public String searchPage(ModelMap model){
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        return "search";
    }
    @RequestMapping(value = "/searchresult")
    public String search(
            @RequestParam Integer idInt,
            ModelMap model){
        Intersection i = intersectionDao.getById(idInt);
        model.addAttribute("intersection", i);
        TrafficSignalController tsc = i.getTrafficSignalController();
        model.addAttribute("tsc", tsc);
        List<Access> accesses = i.getAccessList();
        model.addAttribute("accesses", accesses);
        List<Detector> detectors = i.getDetectorList();
        model.addAttribute("detectors", detectors);
        List<Pole> poles = i.getPoleList();
        model.addAttribute("poles", poles);
        List<SignalHead> signalHeads = i.getSignalHeadList();
        model.addAttribute("signalheads", signalHeads);
        List<PedestrianPushButton> pedestrianPushButtons = i.getPedestrianPushButtonList();
        model.addAttribute("pedestrianpushbuttons", pedestrianPushButtons);
        List<PedestrianDisplay> pedestrianDisplays = i.getPedestrianDisplayList();
        model.addAttribute("pedestriandisplays", pedestrianDisplays);
        
        return "searchresult";
    }
    
    @RequestMapping(value = "/export")
    public String exportPage(ModelMap model){
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        return "export";
    }
    
    @RequestMapping(value = "/exportresult",method = RequestMethod.POST)
    public void export(
//            @RequestParam Integer idInt,
            @RequestParam(required = false) String detector,
            @RequestParam(required = false) String pole,
            @RequestParam(required = false) String signalHead,
            @RequestParam(required = false) String pedestrianDisplay,
            @RequestParam(required = false) String pedestrianPushButton,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model){
        
        File file = new File("Intersections.xlsx");
        response.setContentType("application/octet-stream");//vnd.ms-excel
        response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
        
        String[] idInt = request.getParameterValues("idInt");
        
        
        
        XSSFWorkbook wb = exportExcel.exportTo(idInt, detector, pole, signalHead, pedestrianDisplay, pedestrianPushButton);
        
        try(
            ServletOutputStream fos = response.getOutputStream();
                ){
            
            wb.write(fos);
            
        }catch(IOException e){
            
        }    
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("prvi");
//            
//        exportExcel.exportTo(idInt, detector, pole, signalHead, pedestrianDisplay, pedestrianPushButton);   
//        Row row = sheet.createRow(5);
//        
//        Cell cell = row.createCell(5);
//        cell.setCellValue("Bla bla bla");
//
//        try (
//                ServletOutputStream fos = response.getOutputStream();
//                ){
//            
//            workbook.write(fos);
//        } catch (IOException e) {
//        }
        
    }
}
