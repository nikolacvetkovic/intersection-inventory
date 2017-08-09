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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/insert")
public class InsertController {
    
    
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
   
    
    
    @RequestMapping(value = "/intersection", method = RequestMethod.GET)
    public String intersectionInputPage(ModelMap model){
        String naslov = "Unos raskrsnice";
        model.addAttribute("naslov", naslov);
        return "intersectioninput";
    }
    @RequestMapping(value = "/intersection", method = RequestMethod.POST)
    public String intersectionInput(
            @RequestParam String symbol,
            @RequestParam String title,
            @RequestParam String xCoordinate,
            @RequestParam String yCoordinate,
            @RequestParam MultipartFile pdf,
            HttpServletRequest request,
            ModelMap model) throws FileNotFoundException, IOException{
        String naslov = "Unos raskrsnice";
        model.addAttribute("naslov", naslov);
        
        Intersection i = new Intersection();
        if(pdf!=null&&!pdf.isEmpty()) {
            i.setPdf(pdf.getOriginalFilename());
            //need path from config file
            pdf.transferTo(new File(pdf.getOriginalFilename()));
        }
        
        i.setSymbol(Integer.parseInt(symbol));
        i.setTitle(title);
        i.setXcoordinate(new BigDecimal(xCoordinate));
        i.setYcoordinate(new BigDecimal(yCoordinate));
        i.setPdf(pdf.getOriginalFilename());
        intersectionDao.insert(i);
        
        return "intersectioninput";
    }     
    @RequestMapping(value = "/trafficsignalcontroller", method = RequestMethod.GET)
    public String trafficSignalControllerInputPage(ModelMap model){
        String naslov = "Unos upravljačkog uređaja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        return "trafficsignalcontrollerinput";
    }
    @RequestMapping(value = "/trafficsignalcontroller", method = RequestMethod.POST)
    public String trafficSignalControllerInput(
            @RequestParam Integer id,
            @RequestParam String manufacturer,
            @RequestParam String modelCon,
            @RequestParam String yearOfProduction,
            ModelMap model){
        String naslov = "Unos upravljačkog uređaja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        TrafficSignalController tsc = new TrafficSignalController();
        Intersection i = intersectionDao.getById(id);
        tsc.setIntersection(i);
        tsc.setManufacturer(manufacturer);
        tsc.setModel(modelCon);
        tsc.setYearOfProduction(Integer.parseInt(yearOfProduction));
        trafficSignalControllerDao.insert(tsc);
        
        return "trafficsignalcontrollerinput";
    }
    @RequestMapping(value = "/pole", method = RequestMethod.GET)
    public String poleInputPage(
            @RequestParam(required = false) Integer idInt,
            ModelMap model){
        String naslov = "Unos stubova";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Access> accesses = accessDao.getByIntersection(i);
            model.addAttribute("accesses", accesses);
        } else {
            List<Access> accesses = accessDao.getAll();
            model.addAttribute("accesses", accesses);
        }
        
        return "poleinput";
    }
    @RequestMapping(value = "/pole", method = RequestMethod.POST)
    public String poleInput(
            @RequestParam Integer idInt,
            @RequestParam Integer idAcc,
            @RequestParam String symbol,
            @RequestParam String xcoordinate,
            @RequestParam String ycoordinate,
            @RequestParam String type,
            @RequestParam String modelPole,
            @RequestParam String manufacturer,
            ModelMap model){
        String naslov = "Unos stubova";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Access a = accessDao.getById(idAcc);
        
        Pole p = new Pole();
        p.setSymbol(symbol);
        p.setXcoordinate(new BigDecimal(xcoordinate));
        p.setYcoordinate(new BigDecimal(ycoordinate));
        p.setType(type);
        p.setModel(modelPole);
        p.setManufacturer(manufacturer);
        p.setIntersection(i);
        p.setAccess(a);
        poleDao.insert(p);
        
        return "poleinput";
    }
    @RequestMapping(value = "/detector", method = RequestMethod.GET)
    public String detectorInputPage(
            @RequestParam(required = false) Integer idInt,
            ModelMap model){
        String naslov = "Unos detektora";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Access> accesses = accessDao.getByIntersection(i);
            model.addAttribute("accesses", accesses);
        } else {
            List<Access> accesses = accessDao.getAll();
            model.addAttribute("accesses", accesses);
        }
        
        return "detectorinput";
    }
    @RequestMapping(value = "/detector", method = RequestMethod.POST)
    public String detectorInput(
            @RequestParam Integer idInt,
            @RequestParam Integer idAcc,
            @RequestParam String symbol,
            @RequestParam String type,
            @RequestParam String purpose,
            @RequestParam String xDimension,
            @RequestParam String yDimension,
            @RequestParam String position,
            ModelMap model){
        String naslov = "Unos detektora";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Access a = accessDao.getById(idAcc);
        
        Detector d = new Detector();
        d.setSymbol(symbol);
        d.setType(type);
        d.setPurpose(purpose);
        d.setxDimension(new BigDecimal(xDimension));
        d.setyDimension(new BigDecimal(yDimension));
        d.setPosition(Integer.parseInt(position));
        d.setAccess(a);
        d.setIntersection(i);
        detectorDao.insert(d);
        
        return "detectorinput";
    }
    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public String accessInputPage(ModelMap model){
        String naslov = "Unos prilaza";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        return "accessinput";
    }
    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public String accessInput(
            @RequestParam Integer id,
            @RequestParam String symbol,
            @RequestParam String title,
            ModelMap model){
        String naslov = "Unos prilaza";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Access a = new Access();
        a.setIntersection(intersectionDao.getById(id));
        a.setSymbol(Integer.parseInt(symbol));
        a.setTitle(title);
        accessDao.insert(a);
        
        
        return "accessinput";
    }
    @RequestMapping(value = "/signalhead",method = RequestMethod.GET)
    public String signalHeadInputPage(
            @RequestParam(required = false) Integer idInt,
            ModelMap model){
        String naslov = "Unos lanterni";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Pole> poles = poleDao.getByIntersection(i);
            model.addAttribute("poles", poles);
        } else {
            List<Pole> poles = poleDao.getAll();
            model.addAttribute("poles", poles);
        }
        
        return "signalheadinput";
    }
    @RequestMapping(value = "/signalhead",method = RequestMethod.POST)
    public String signalHeadInput(
            @RequestParam Integer idInt,
            @RequestParam Integer idPole,
            @RequestParam String symbol,
            @RequestParam String type,
            @RequestParam String importance,
            @RequestParam String dimension,
            @RequestParam String contrasttable,
            @RequestParam String sound,
            @RequestParam String manufacturer,
            @RequestParam String modelSig,
            ModelMap model){
        String naslov = "Unos lanterni";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        Intersection i = intersectionDao.getById(idInt);
        Pole p = poleDao.getById(idPole);
        
        SignalHead sg = new SignalHead();
        sg.setSymbol(symbol);
        sg.setType(type);
        sg.setImportance(importance);
        sg.setDimension(dimension);
        sg.setContrasttable(contrasttable);
        sg.setSound(sound);
        sg.setManufacturer(manufacturer);
        sg.setModel(modelSig);
        sg.setIntersection(i);
        sg.setPole(p);
        signalHeadDao.insert(sg);
        
        return "signalheadinput";
    }
    @RequestMapping(value = "/pedestrianpushbutton",method = RequestMethod.GET)
    public String pedestrianPushButtonInputPage(
            @RequestParam(required = false) Integer idInt,
            ModelMap model){
        String naslov = "Unos pešačkih tastera";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Pole> poles = poleDao.getByIntersection(i);
            model.addAttribute("poles", poles);
        } else {
            List<Pole> poles = poleDao.getAll();
            model.addAttribute("poles", poles);
        }        
        
        return "pedestrianpushbuttoninput";
    }
    @RequestMapping(value = "/pedestrianpushbutton",method = RequestMethod.POST)
    public String pedestrianPushButtonInput(
            @RequestParam Integer idInt,
            @RequestParam Integer idPole,
            @RequestParam String symbol,
            @RequestParam String sound,
            @RequestParam String light,
            @RequestParam String locatortone,
            @RequestParam String manufacturer,
            @RequestParam String modelPed,
            ModelMap model){
        String naslov = "Unos pešačkih tastera";
        model.addAttribute("naslov", naslov);
        Intersection i = intersectionDao.getById(idInt);
        Pole p = poleDao.getById(idPole);
        
        PedestrianPushButton ppb = new PedestrianPushButton();
        ppb.setSymbol(symbol);
        ppb.setSound(sound);
        ppb.setLight(light);
        ppb.setLocatortone(locatortone);
        ppb.setManufacturer(manufacturer);
        ppb.setModel(modelPed);
        ppb.setIntersection(i);
        ppb.setPole(p);
        pedestrianPushButtonDao.insert(ppb);
        
        return "pedestrianpushbuttoninput";
    }
    @RequestMapping(value = "/pedestriandisplay",method = RequestMethod.GET)
    public String pedestrianDisplayInputPage(
            @RequestParam(required = false) Integer idInt,
            ModelMap model){
        String naslov = "Unos pešačkih displeja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Pole> poles = poleDao.getByIntersection(i);
            model.addAttribute("poles", poles);
        } else {
            List<Pole> poles = poleDao.getAll();
            model.addAttribute("poles", poles);
        }
        
        return "pedestriandisplayinput";
    }
    @RequestMapping(value = "/pedestriandisplay",method = RequestMethod.POST)
    public String pedestrianDisplayInput(
            @RequestParam Integer idInt,
            @RequestParam Integer idPole,
            @RequestParam String symbol,
            @RequestParam String typefunction,
            @RequestParam String manufacturer,
            @RequestParam String modelDis,
            ModelMap model){
        String naslov = "Unos pešačkih displeja";
        model.addAttribute("naslov", naslov);
        Intersection i = intersectionDao.getById(idInt);
        Pole p = poleDao.getById(idPole);
        
        PedestrianDisplay pd = new PedestrianDisplay();
        pd.setSymbol(symbol);
        pd.setTypefunction(typefunction);
        pd.setManufacturer(manufacturer);
        pd.setModel(modelDis);
        pd.setIntersection(i);
        pd.setPole(p);
        pedestrianDisplayDao.insert(pd);
        
        return "pedestriandisplayinput";
    }
    
}
