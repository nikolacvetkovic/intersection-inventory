package com.intersections.controller;

import com.intersections.model.*;
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
public class SiteController {
    
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
    
    @RequestMapping("/")
    public String index(){
            
         return "index";
    }
    @RequestMapping(value = "/intersectioninput", method = RequestMethod.GET)
    public String intersectionInputPage(ModelMap model){
        String naslov = "Unos raskrsnice";
        model.addAttribute("naslov", naslov);
        return "intersectioninput";
    }
    @RequestMapping(value = "/intersectioninput", method = RequestMethod.POST)
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
            String filePath = request.getServletContext().getRealPath("assets/pdf");
            FileOutputStream fos = new FileOutputStream(filePath+"/"+pdf.getOriginalFilename());
            i.setPdf(pdf.getOriginalFilename());
            fos.write(pdf.getBytes());
            fos.close();
        }
        
        i.setSymbol(Integer.parseInt(symbol));
        i.setTitle(title);
        i.setXcoordinate(new BigDecimal(xCoordinate));
        i.setYcoordinate(new BigDecimal(yCoordinate));
        i.setPdf(pdf.getOriginalFilename());
        intersectionDao.insert(i);
        
        return "intersectioninput";
    }
    @RequestMapping(value = "/trafficsignalcontrollerinput", method = RequestMethod.GET)
    public String trafficSignalControllerInputPage(ModelMap model){
        String naslov = "Unos upravljačkog uređaja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        return "trafficsignalcontrollerinput";
    }
    @RequestMapping(value = "/trafficsignalcontrollerinput", method = RequestMethod.POST)
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
        tsc.setIntersection(intersectionDao.getById(id));
        tsc.setManufacturer(manufacturer);
        tsc.setModel(modelCon);
        tsc.setYearOfProduction(Integer.parseInt(yearOfProduction));
        trafficSignalControllerDao.insert(tsc);
        
        return "trafficsignalcontrollerinput";
    }
    @RequestMapping(value = "/poleinput", method = RequestMethod.GET)
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
    @RequestMapping(value = "/poleinput", method = RequestMethod.POST)
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
    @RequestMapping(value = "/detectorinput", method = RequestMethod.GET)
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
    @RequestMapping(value = "/detectorinput", method = RequestMethod.POST)
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
    @RequestMapping(value = "/accessinput", method = RequestMethod.GET)
    public String accessInputPage(ModelMap model){
        String naslov = "Unos prilaza";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        return "accessinput";
    }
    @RequestMapping(value = "/accessinput", method = RequestMethod.POST)
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
        intersectionDao.getById(id).getAccessList().add(a);
        
        
        return "accessinput";
    }
    @RequestMapping(value = "/signalheadinput",method = RequestMethod.GET)
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
    @RequestMapping(value = "/signalheadinput",method = RequestMethod.POST)
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
    @RequestMapping(value = "/pedestrianpushbuttoninput",method = RequestMethod.GET)
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
    @RequestMapping(value = "/pedestrianpushbuttoninput",method = RequestMethod.POST)
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
    @RequestMapping(value = "/pedestriandisplayinput",method = RequestMethod.GET)
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
    @RequestMapping(value = "/pedestriandisplayinput",method = RequestMethod.POST)
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
    // *************************************************************************************************************************************//
    // ********************************************************** U P D A T E **************************************************************//
    
    @RequestMapping(value = "/intersectionupdate", method = RequestMethod.GET)
    public String intersectionUpdatePage(
            @RequestParam(required = false) Integer idInt,
            ModelMap model){
        String naslov = "Ažuriranje raskrsnice";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
        }
        return "intersectionupdate";
    }
    @RequestMapping(value = "/intersectionupdate", method = RequestMethod.POST)
    public String intersectionUpdate(
            @RequestParam Integer idInt,
            @RequestParam String symbol,
            @RequestParam String title,
            @RequestParam String xCoordinate,
            @RequestParam String yCoordinate,
            @RequestParam MultipartFile pdf,
            HttpServletRequest request,
            ModelMap model) throws FileNotFoundException, IOException{
        String naslov = "Ažuriranje raskrsnice";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        if(pdf!=null&&!pdf.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("assets/pdf");
            FileOutputStream fos = new FileOutputStream(filePath+"/"+pdf.getOriginalFilename());
            i.setPdf(pdf.getOriginalFilename());
            fos.write(pdf.getBytes());
            fos.close();
        }
        
        i.setSymbol(Integer.parseInt(symbol));
        i.setTitle(title);
        i.setXcoordinate(new BigDecimal(xCoordinate));
        i.setYcoordinate(new BigDecimal(yCoordinate));
        i.setPdf(pdf.getOriginalFilename());
        intersectionDao.update(i);
        
        return "intersectionupdate";
    }
    @RequestMapping(value = "/trafficsignalcontrollerupdate", method = RequestMethod.GET)
    public String trafficSignalControllerUpdatePage(
            @RequestParam(required = false) Integer idInt,
            ModelMap model){
        String naslov = "Ažuriranje upravljačkog uređaja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
        }
        
        return "trafficsignalcontrollerupdate";
    }
    @RequestMapping(value = "/trafficsignalcontrollerupdate", method = RequestMethod.POST)
    public String trafficSignalControllerUpdate(
            @RequestParam Integer idInt,
            @RequestParam Integer idCon,
            @RequestParam String manufacturer,
            @RequestParam String modelCon,
            @RequestParam String yearOfProduction,
            ModelMap model){
        String naslov = "Ažuriranje upravljačkog uređaja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        TrafficSignalController tsc = trafficSignalControllerDao.getById(idCon);
        tsc.setIntersection(i);
        tsc.setManufacturer(manufacturer);
        tsc.setModel(modelCon);
        tsc.setYearOfProduction(Integer.parseInt(yearOfProduction));
        trafficSignalControllerDao.update(tsc);
        
        return "trafficsignalcontrollerupdate";
    }
    @RequestMapping(value = "/accessupdate", method = RequestMethod.GET)
    public String accessUpdatePage(
            @RequestParam(required = false) Integer idInt,
            @RequestParam(required = false) Integer idAcc,
            ModelMap model){
        String naslov = "Ažuriranje prilaza";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null&&idAcc!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Access a = accessDao.getById(idAcc);
            model.addAttribute("selectedAccess", a);
        }
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Access> accesses = accessDao.getByIntersection(i);
            model.addAttribute("accesses", accesses);
        }
        
        return "accessupdate";
    }
    @RequestMapping(value = "/accessupdate", method = RequestMethod.POST)
    public String accessUpdate(
            @RequestParam Integer idInt,
            @RequestParam Integer idAccess,
            @RequestParam String symbol,
            @RequestParam String title,
            ModelMap model){
        String naslov = "Ažuriranje prilaza";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Access a = accessDao.getById(idAccess);
        a.setIntersection(i);
        a.setSymbol(Integer.parseInt(symbol));
        a.setTitle(title);
        accessDao.update(a);
        
        return "accessupdate";
    }
    @RequestMapping(value = "/detectorupdate", method = RequestMethod.GET)
    public String detectorUpdatePage(
            @RequestParam(required = false) Integer idInt,
            @RequestParam(required = false) Integer idAcc,
            @RequestParam(required = false) Integer idDet,
            ModelMap model){
        String naslov = "Ažuriranje detektora";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null&&idAcc!=null&&idDet!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Access a = accessDao.getById(idAcc);
            model.addAttribute("selectedAccess", a);
            Detector d = detectorDao.getById(idDet);
            model.addAttribute("selectedDetector", d);
        }
        if(idInt!=null&&idAcc!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Access a = accessDao.getById(idAcc);
            model.addAttribute("selectedAccess", a);
            List<Detector> detectors = detectorDao.getByAccess(a);
            model.addAttribute("detectors", detectors);
        }
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Access> accesses = accessDao.getByIntersection(i);
            model.addAttribute("accesses", accesses);
        }
        
        return "detectorupdate";
    }
    @RequestMapping(value = "/detectorupdate", method = RequestMethod.POST)
    public String detectorUpdate(
            @RequestParam Integer idInt,
            @RequestParam Integer idAcc,
            @RequestParam Integer idDetector,
            @RequestParam String symbol,
            @RequestParam String type,
            @RequestParam String purpose,
            @RequestParam String xDimension,
            @RequestParam String yDimension,
            @RequestParam String position,
            ModelMap model){
        String naslov = "Ažuriranje detektora";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Access a = accessDao.getById(idAcc);
        Detector d = detectorDao.getById(idDetector);
        d.setSymbol(symbol);
        d.setType(type);
        d.setPurpose(purpose);
        d.setxDimension(new BigDecimal(xDimension));
        d.setyDimension(new BigDecimal(yDimension));
        d.setPosition(Integer.parseInt(position));
        d.setAccess(a);
        d.setIntersection(i);
        detectorDao.update(d);
        
        return "detectorupdate";
    }
    @RequestMapping(value = "/poleupdate", method = RequestMethod.GET)
    public String poleUpdatePage(
            @RequestParam(required = false) Integer idInt,
            @RequestParam(required = false) Integer idAcc,
            @RequestParam(required = false) Integer idPol,
            ModelMap model){
        String naslov = "Ažuriranje stubova";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null&&idAcc!=null&&idPol!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Access a = accessDao.getById(idAcc);
            model.addAttribute("selectedAccess", a);
            Pole p = poleDao.getById(idPol);
            model.addAttribute("selectedPole", p);
        }
        if(idInt!=null&&idAcc!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Access a = accessDao.getById(idAcc);
            model.addAttribute("selectedAccess", a);
            List<Pole> poles = poleDao.getByAccess(a);
            model.addAttribute("poles", poles);
        }
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Access> accesses = accessDao.getByIntersection(i);
            model.addAttribute("accesses", accesses);
        }
        
        return "poleupdate";
    }
    @RequestMapping(value = "/poleupdate", method = RequestMethod.POST)
    public String poleUpdate(
            @RequestParam Integer idInt,
            @RequestParam Integer idAcc,
            @RequestParam Integer idPole,
            @RequestParam String symbol,
            @RequestParam String xcoordinate,
            @RequestParam String ycoordinate,
            @RequestParam String type,
            @RequestParam String modelPole,
            @RequestParam String manufacturer,
            ModelMap model){
        String naslov = "Ažuriranje stubova";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Access a = accessDao.getById(idAcc);
        Pole p = poleDao.getById(idPole);
        p.setSymbol(symbol);
        p.setXcoordinate(new BigDecimal(xcoordinate));
        p.setYcoordinate(new BigDecimal(ycoordinate));
        p.setType(type);
        p.setModel(modelPole);
        p.setManufacturer(manufacturer);
        p.setIntersection(i);
        p.setAccess(a);
        poleDao.update(p);
        
        return "poleupdate";
    }
    @RequestMapping(value = "/signalheadupdate", method = RequestMethod.GET)
    public String signalHeadUpdatePage(
            @RequestParam(required = false) Integer idInt,
            @RequestParam(required = false) Integer idPol,
            @RequestParam(required = false) Integer idSig,
            ModelMap model){
        String naslov = "Ažuriranje lanterni";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null&&idPol!=null&&idSig!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Pole p = poleDao.getById(idPol);
            model.addAttribute("selectedPole", p);
            SignalHead sg = signalHeadDao.getById(idSig);
            model.addAttribute("selectedSignalHead", sg);
        }
        if(idInt!=null&&idPol!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Pole p = poleDao.getById(idPol);
            model.addAttribute("selectedPole", p);
            List<SignalHead> signalHeads = signalHeadDao.getByPole(p);
            model.addAttribute("signalheads", signalHeads);
        }
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Pole> poles = poleDao.getByIntersection(i);
            model.addAttribute("poles", poles);
        }
        
        return "signalheadupdate";
    }
    @RequestMapping(value = "/signalheadupdate", method = RequestMethod.POST)
    public String signalHeadUpdate(
            @RequestParam Integer idInt,
            @RequestParam Integer idPol,
            @RequestParam Integer idSignal,
            @RequestParam String symbol,
            @RequestParam String type,
            @RequestParam String importance,
            @RequestParam String dimension,
            @RequestParam String contrasttable,
            @RequestParam String sound,
            @RequestParam String manufacturer,
            @RequestParam String modelSig,
            ModelMap model){
        String naslov = "Ažuriranje lanterni";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Pole p = poleDao.getById(idPol);
        SignalHead sg = signalHeadDao.getById(idSignal);
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
        signalHeadDao.update(sg);
        
        return "signalheadupdate";
    }
    @RequestMapping(value = "/pedestrianpushbuttonupdate", method = RequestMethod.GET)
    public String pedestrianPushButtonUpdatePage(
            @RequestParam(required = false) Integer idInt,
            @RequestParam(required = false) Integer idPol,
            @RequestParam(required = false) Integer idPed,
            ModelMap model){
        String naslov = "Ažuriranje pešačkih tastera";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null&&idPol!=null&&idPed!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Pole p = poleDao.getById(idPol);
            model.addAttribute("selectedPole", p);
            PedestrianPushButton ppb = pedestrianPushButtonDao.getById(idPed);
            model.addAttribute("selectedppb", ppb);
        }
        if(idInt!=null&&idPol!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Pole p = poleDao.getById(idPol);
            model.addAttribute("selectedPole", p);
            List<PedestrianPushButton> ppbs = pedestrianPushButtonDao.getByPole(p);
            model.addAttribute("ppbs", ppbs);
        }
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Pole> poles = poleDao.getByIntersection(i);
            model.addAttribute("poles", poles);
        }
        
        return "pedestrianpushbuttonupdate";
    }
    @RequestMapping(value = "/pedestrianpushbuttonupdate", method = RequestMethod.POST)
    public String pedestrianPushButtonUpdate(
            @RequestParam Integer idInt,
            @RequestParam Integer idPol,
            @RequestParam Integer idPedestrian,
            @RequestParam String symbol,
            @RequestParam String sound,
            @RequestParam String light,
            @RequestParam String locatortone,
            @RequestParam String manufacturer,
            @RequestParam String modelPed,
            ModelMap model){
        String naslov = "Ažuriranje pešačkih tastera";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Pole p = poleDao.getById(idPol);
        PedestrianPushButton ppb = pedestrianPushButtonDao.getById(idPedestrian);
        ppb.setSymbol(symbol);
        ppb.setSound(sound);
        ppb.setLight(light);
        ppb.setLocatortone(locatortone);
        ppb.setManufacturer(manufacturer);
        ppb.setModel(modelPed);
        ppb.setIntersection(i);
        ppb.setPole(p);
        pedestrianPushButtonDao.update(ppb);
        
        return "pedestrianpushbuttonupdate";
    }
    @RequestMapping(value = "/pedestriandisplayupdate", method = RequestMethod.GET)
    public String pedestrianDisplayUpdatePage(
            @RequestParam(required = false) Integer idInt,
            @RequestParam(required = false) Integer idPol,
            @RequestParam(required = false) Integer idPed,
            ModelMap model){
        String naslov = "Ažuriranje pešačkih displeja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        if(idInt!=null&&idPol!=null&&idPed!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Pole p = poleDao.getById(idPol);
            model.addAttribute("selectedPole", p);
            PedestrianDisplay pd = pedestrianDisplayDao.getById(idPed);
            model.addAttribute("selectedpd", pd);
        }
        if(idInt!=null&&idPol!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            Pole p = poleDao.getById(idPol);
            model.addAttribute("selectedPole", p);
            List<PedestrianDisplay> pds = pedestrianDisplayDao.getByPole(p);
            model.addAttribute("pds", pds);
        }
        if(idInt!=null){
            Intersection i = intersectionDao.getById(idInt);
            model.addAttribute("selectedIntersection", i);
            List<Pole> poles = poleDao.getByIntersection(i);
            model.addAttribute("poles", poles);
        }
        
        return "pedestriandisplayupdate";
    }
    @RequestMapping(value = "/pedestriandisplayupdate", method = RequestMethod.POST)
    public String pedestrianDisplayUpdate(
            @RequestParam Integer idInt,
            @RequestParam Integer idPol,
            @RequestParam Integer idPedestrian,
            @RequestParam String symbol,
            @RequestParam String typefunction,
            @RequestParam String manufacturer,
            @RequestParam String modelDis,
            ModelMap model){
        String naslov = "Ažuriranje pešačkih displeja";
        model.addAttribute("naslov", naslov);
        List<Intersection> intersections = intersectionDao.getAll();
        model.addAttribute("intersections", intersections);
        
        Intersection i = intersectionDao.getById(idInt);
        Pole p = poleDao.getById(idPol);
        PedestrianDisplay pd = pedestrianDisplayDao.getById(idPedestrian);
        pd.setSymbol(symbol);
        pd.setTypefunction(typefunction);
        pd.setManufacturer(manufacturer);
        pd.setModel(modelDis);
        pd.setIntersection(i);
        pd.setPole(p);
        pedestrianDisplayDao.update(pd);
        
        return "pedestriandisplayupdate";
    }
}
