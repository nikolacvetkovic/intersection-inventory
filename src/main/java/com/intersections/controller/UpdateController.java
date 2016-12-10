/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intersections.controller;

import com.intersections.model.AccessDao;
import com.intersections.model.DetectorDao;
import com.intersections.model.Intersection;
import com.intersections.model.IntersectionDao;
import com.intersections.model.PedestrianDisplayDao;
import com.intersections.model.PedestrianPushButtonDao;
import com.intersections.model.PoleDao;
import com.intersections.model.SignalHeadDao;
import com.intersections.model.TrafficSignalControllerDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author cvele
 */
@Controller
@RequestMapping("/update")
public class UpdateController {
    
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
    
}
