package com.intersections.excelservice;

import com.intersections.dao.AccessDao;
import com.intersections.dao.DetectorDao;
import com.intersections.dao.IntersectionDao;
import com.intersections.dao.PedestrianDisplayDao;
import com.intersections.dao.PedestrianPushButtonDao;
import com.intersections.dao.PoleDao;
import com.intersections.dao.SignalHeadDao;
import com.intersections.dao.TrafficSignalControllerDao;
import com.intersections.model.Detector;
import com.intersections.model.Intersection;
import com.intersections.model.PedestrianDisplay;
import com.intersections.model.PedestrianPushButton;
import com.intersections.model.Pole;
import com.intersections.model.SignalHead;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExportExcel {
    
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
    
    public XSSFWorkbook exportTo(String[] idInt, String detector, String pole, String signalHead, String pedestrianDisplay, String pedestrianPushButton){
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Prvi");
                
        if((Boolean.parseBoolean(pole)==true) && (Boolean.parseBoolean(signalHead)==true)){
            return wb = exportPolesSignalHeads(idInt);
        }
        if((Boolean.parseBoolean(pole)==true) && (Boolean.parseBoolean(pedestrianDisplay)==true)){
            return wb = exportPolesPedestrianDisplays(idInt);
        }
        if((Boolean.parseBoolean(pole)==true) && (Boolean.parseBoolean(pedestrianPushButton)==true)){
            return wb = exportPolesPedestrianPushButtons(idInt);
        }
        if(Boolean.parseBoolean(detector)==true){
            return wb = exportDetectors(idInt);
        }
        if(Boolean.parseBoolean(pole)==true){
            return wb = exportPoles(idInt);
        }
        
       return wb;
    }
    
    
    // ********************************************************************** SAMO DETEKTORI ********************************************************************* //
    private XSSFWorkbook exportDetectors(String[] idInt){
        List<Intersection> interList = new ArrayList<>();
        for(String s : idInt){
            interList.add(intersectionDao.getById(Integer.parseInt(s)));
        }
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Prvi");
        
        Row r0 = sheet.createRow(0);
        r0.createCell(3).setCellValue("Naziv raskrsnice");
        r0.createCell(4).setCellValue("Upravljački uređaj");
        
        int rowControl = 1;
       
        for(int i=0; i<interList.size();i++){
            Intersection intersection = interList.get(i);
            Row r = sheet.createRow(rowControl++);
            r.createCell(3).setCellValue(intersection.getTitle());
            r.createCell(4).setCellValue(intersection.getTrafficSignalController().getManufacturer() + " " + intersection.getTrafficSignalController().getModel());
        }
        
        Row r = sheet.createRow(rowControl++);
        r.createCell(0).setCellValue("Redni broj");
        r.createCell(1).setCellValue("Oznaka raskrsnice");
        r.createCell(2).setCellValue("Oznaka prilaza");
        r.createCell(3).setCellValue("Oznaka detektora");
        r.createCell(4).setCellValue("Tip detektora");
        r.createCell(5).setCellValue("Namena detektora");
        r.createCell(6).setCellValue("Udaljenost od linije zaustavljanja");
        r.createCell(7).setCellValue("Dužina petlje");
        r.createCell(8).setCellValue("Širina petlje");
        
        int rowCount = 1;  
        for(int i=0;i<interList.size();i++){
            
            List<Detector> detectors = interList.get(i).getDetectorList();
            for (int j = 0; j < detectors.size(); j++) {
            
                Detector d = detectors.get(j);
                Row rd = sheet.createRow(rowControl++);

                rd.createCell(0).setCellValue(rowCount++);
                rd.createCell(1).setCellValue(d.getIntersection().getSymbol());
                rd.createCell(2).setCellValue(d.getAccess().getSymbol());
                rd.createCell(3).setCellValue(d.getSymbol());
                rd.createCell(4).setCellValue(d.getType());
                rd.createCell(5).setCellValue(d.getPurpose());
                rd.createCell(6).setCellValue(d.getPosition());
                rd.createCell(7).setCellValue(String.valueOf(d.getxDimension()));
                rd.createCell(8).setCellValue(String.valueOf(d.getyDimension()));
            }
        }
        
        for(int j=0; j<9;j++){
            sheet.autoSizeColumn(j);
        }
        return wb;
    }
    
    // ********************************************************************** SAMO STUBOVI ********************************************************************* //
    private XSSFWorkbook exportPoles(String[] idInt){
        List<Intersection> interList = new ArrayList<>();
        for(String s : idInt){
            interList.add(intersectionDao.getById(Integer.parseInt(s)));
        }
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Prvi");
        
        Row r0 = sheet.createRow(0);
        r0.createCell(3).setCellValue("Naziv raskrsnice");
        r0.createCell(4).setCellValue("Upravljački uređaj");
        
        int rowControl = 1;
       
        for(int i=0; i<interList.size();i++){
            Intersection intersection = interList.get(i);
            Row r = sheet.createRow(rowControl++);
            r.createCell(3).setCellValue(intersection.getTitle());
            r.createCell(4).setCellValue(intersection.getTrafficSignalController().getManufacturer() + " " + intersection.getTrafficSignalController().getModel());
        }
        
        Row r = sheet.createRow(rowControl++);
        r.createCell(0).setCellValue("Redni broj");
        r.createCell(1).setCellValue("Oznaka raskrsnice");
        r.createCell(2).setCellValue("Oznaka prilaza");
        r.createCell(3).setCellValue("Oznaka stuba");
        r.createCell(4).setCellValue("Tip stuba");
        r.createCell(5).setCellValue("Model stuba");
        r.createCell(6).setCellValue("Proizvošač");
        r.createCell(7).setCellValue("Koordinata X");
        r.createCell(8).setCellValue("Koordinata Y");
        
        int rowCount = 1;  
        for(int i=0;i<interList.size();i++){
            
            List<Pole> poles = interList.get(i).getPoleList();
            for (int j = 0; j < poles.size(); j++) {
            
                Pole p = poles.get(j);
                Row rd = sheet.createRow(rowControl++);

                rd.createCell(0).setCellValue(rowCount++);
                rd.createCell(1).setCellValue(p.getIntersection().getSymbol());
                rd.createCell(2).setCellValue(p.getAccess().getSymbol());
                rd.createCell(3).setCellValue(p.getSymbol());
                rd.createCell(4).setCellValue(p.getType());
                rd.createCell(5).setCellValue(p.getModel());
                rd.createCell(6).setCellValue(p.getManufacturer());
                rd.createCell(7).setCellValue(String.valueOf(p.getXcoordinate()));
                rd.createCell(8).setCellValue(String.valueOf(p.getYcoordinate()));
            }
        }
        
        for(int j=0; j<9;j++){
            sheet.autoSizeColumn(j);
        }
        return wb;
    }
    // *************************************************************** STUBOVI - LANTERNE *********************************************************************** //
    private XSSFWorkbook exportPolesSignalHeads(String[] idInt){
        List<Intersection> interList = new ArrayList<>();
        for(String s : idInt){
            interList.add(intersectionDao.getById(Integer.parseInt(s)));
        }
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Prvi");
        
        Row r0 = sheet.createRow(0);
        r0.createCell(3).setCellValue("Naziv raskrsnice");
        r0.createCell(4).setCellValue("Upravljački uređaj");
        
        int rowControl = 1;
       
        for(int i=0; i<interList.size();i++){
            Intersection intersection = interList.get(i);
            Row r = sheet.createRow(rowControl++);
            r.createCell(3).setCellValue(intersection.getTitle());
            r.createCell(4).setCellValue(intersection.getTrafficSignalController().getManufacturer() + " " + intersection.getTrafficSignalController().getModel());
        }
        
        Row r = sheet.createRow(rowControl++);
        r.createCell(0).setCellValue("Redni broj");
        r.createCell(1).setCellValue("Oznaka raskrsnice");
        r.createCell(2).setCellValue("Oznaka prilaza");
        r.createCell(3).setCellValue("Oznaka stuba");
        r.createCell(4).setCellValue("Tip stuba");
        r.createCell(5).setCellValue("Oznaka lanterne");
        r.createCell(6).setCellValue("Tip lanterne");
        r.createCell(7).setCellValue("Hijerarhija");
        r.createCell(8).setCellValue("Dimenzija lanterne");
        r.createCell(9).setCellValue("Kontrastna tabla");
        r.createCell(10).setCellValue("Davač zvuka");
        r.createCell(11).setCellValue("Proizvođač");
        r.createCell(11).setCellValue("Model");
        
        int rowCount = 1;  
        for(int i=0;i<interList.size();i++){
            
            List<Pole> poles = interList.get(i).getPoleList();
            for(int k=0;k<poles.size();k++){
                
                List<SignalHead> signalHeads = poles.get(k).getSignalHeadList();
                for (int j = 0; j < signalHeads.size(); j++) {

                    SignalHead sg = signalHeads.get(j);
                    Row rd = sheet.createRow(rowControl++);

                    rd.createCell(0).setCellValue(rowCount++);
                    rd.createCell(1).setCellValue(sg.getIntersection().getSymbol());
                    rd.createCell(2).setCellValue(sg.getPole().getAccess().getSymbol());
                    rd.createCell(3).setCellValue(sg.getPole().getSymbol());
                    rd.createCell(4).setCellValue(sg.getPole().getType());
                    rd.createCell(5).setCellValue(sg.getSymbol());
                    rd.createCell(6).setCellValue(sg.getImportance());
                    rd.createCell(7).setCellValue(sg.getDimension());
                    rd.createCell(8).setCellValue(sg.getContrasttable());
                    rd.createCell(9).setCellValue(sg.getSound());
                    rd.createCell(10).setCellValue(sg.getManufacturer());
                    rd.createCell(11).setCellValue(sg.getModel());
                }
            }    
        }
        
        for(int j=0; j<12;j++){
            sheet.autoSizeColumn(j);
        }
        return wb;
    }
    // *********************************************************** STUBOVI - PESACKI DISPLEJ ********************************************************************* //
    private XSSFWorkbook exportPolesPedestrianDisplays(String[] idInt){
        List<Intersection> interList = new ArrayList<>();
        for(String s : idInt){
            interList.add(intersectionDao.getById(Integer.parseInt(s)));
        }
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Prvi");
        
        Row r0 = sheet.createRow(0);
        r0.createCell(3).setCellValue("Naziv raskrsnice");
        r0.createCell(4).setCellValue("Upravljački uređaj");
        
        int rowControl = 1;
       
        for(int i=0; i<interList.size();i++){
            Intersection intersection = interList.get(i);
            Row r = sheet.createRow(rowControl++);
            r.createCell(3).setCellValue(intersection.getTitle());
            r.createCell(4).setCellValue(intersection.getTrafficSignalController().getManufacturer() + " " + intersection.getTrafficSignalController().getModel());
        }
        
        Row r = sheet.createRow(rowControl++);
        r.createCell(0).setCellValue("Redni broj");
        r.createCell(1).setCellValue("Oznaka raskrsnice");
        r.createCell(2).setCellValue("Oznaka prilaza");
        r.createCell(3).setCellValue("Oznaka stuba");
        r.createCell(4).setCellValue("Tip stuba");
        r.createCell(5).setCellValue("Oznaka displeja");
        r.createCell(6).setCellValue("Tip funkcionalnosti");
        r.createCell(7).setCellValue("Proizvođač");
        r.createCell(8).setCellValue("Model");
        
        int rowCount = 1;  
        for(int i=0;i<interList.size();i++){
            
            List<Pole> poles = interList.get(i).getPoleList();
            for(int k=0;k<poles.size();k++){
                
                List<PedestrianDisplay> pedDisplays = poles.get(k).getPedestrianDisplayList();
                for (int j = 0; j < pedDisplays.size(); j++) {

                    PedestrianDisplay pd = pedDisplays.get(j);
                    Row rd = sheet.createRow(rowControl++);

                    rd.createCell(0).setCellValue(rowCount++);
                    rd.createCell(1).setCellValue(pd.getIntersection().getSymbol());
                    rd.createCell(2).setCellValue(pd.getPole().getAccess().getSymbol());
                    rd.createCell(3).setCellValue(pd.getPole().getSymbol());
                    rd.createCell(4).setCellValue(pd.getPole().getType());
                    rd.createCell(5).setCellValue(pd.getSymbol());
                    rd.createCell(6).setCellValue(pd.getTypefunction());
                    rd.createCell(7).setCellValue(pd.getManufacturer());
                    rd.createCell(8).setCellValue(pd.getModel());
                }
            }    
        }
        
        for(int j=0; j<9;j++){
            sheet.autoSizeColumn(j);
        }
        return wb;
    }
    // *********************************************************** STUBOVI - PESACKI TASTER ********************************************************************* //
    private XSSFWorkbook exportPolesPedestrianPushButtons(String[] idInt){
        List<Intersection> interList = new ArrayList<>();
        for(String s : idInt){
            interList.add(intersectionDao.getById(Integer.parseInt(s)));
        }
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Prvi");
        
        Row r0 = sheet.createRow(0);
        r0.createCell(3).setCellValue("Naziv raskrsnice");
        r0.createCell(4).setCellValue("Upravljački uređaj");
        
        int rowControl = 1;
       
        for(int i=0; i<interList.size();i++){
            Intersection intersection = interList.get(i);
            Row r = sheet.createRow(rowControl++);
            r.createCell(3).setCellValue(intersection.getTitle());
            r.createCell(4).setCellValue(intersection.getTrafficSignalController().getManufacturer() + " " + intersection.getTrafficSignalController().getModel());
        }
        
        Row r = sheet.createRow(rowControl++);
        r.createCell(0).setCellValue("Redni broj");
        r.createCell(1).setCellValue("Oznaka raskrsnice");
        r.createCell(2).setCellValue("Oznaka prilaza");
        r.createCell(3).setCellValue("Oznaka stuba");
        r.createCell(4).setCellValue("Tip stuba");
        r.createCell(5).setCellValue("Oznaka tastera");
        r.createCell(6).setCellValue("Zvučna potvrda najave");
        r.createCell(7).setCellValue("Svetlosna potvrda najave");
        r.createCell(8).setCellValue("Lokator");
        r.createCell(9).setCellValue("Proizvođač");
        r.createCell(10).setCellValue("Model");
        
        int rowCount = 1;  
        for(int i=0;i<interList.size();i++){
            
            List<Pole> poles = interList.get(i).getPoleList();
            for(int k=0;k<poles.size();k++){
                
                List<PedestrianPushButton> pedPushButtons = poles.get(k).getPedestrianPushButtonList();
                for (int j = 0; j < pedPushButtons.size(); j++) {

                    PedestrianPushButton ppb = pedPushButtons.get(j);
                    Row rd = sheet.createRow(rowControl++);

                    rd.createCell(0).setCellValue(rowCount++);
                    rd.createCell(1).setCellValue(ppb.getIntersection().getSymbol());
                    rd.createCell(2).setCellValue(ppb.getPole().getAccess().getSymbol());
                    rd.createCell(3).setCellValue(ppb.getPole().getSymbol());
                    rd.createCell(4).setCellValue(ppb.getPole().getType());
                    rd.createCell(5).setCellValue(ppb.getSymbol());
                    rd.createCell(6).setCellValue(ppb.getSound());
                    rd.createCell(7).setCellValue(ppb.getLight());
                    rd.createCell(8).setCellValue(ppb.getLocatortone());
                    rd.createCell(9).setCellValue(ppb.getManufacturer());
                    rd.createCell(10).setCellValue(ppb.getModel());
                }
            }    
        }
        
        for(int j=0; j<11;j++){
            sheet.autoSizeColumn(j);
        }
        return wb;
    }
}
