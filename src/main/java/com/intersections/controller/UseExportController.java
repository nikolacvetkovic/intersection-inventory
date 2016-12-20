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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
}
