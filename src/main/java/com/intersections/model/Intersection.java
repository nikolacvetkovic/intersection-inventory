package com.intersections.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "intersections")
@NamedQueries({
    @NamedQuery(name = "Intersection.findAll", query = "SELECT i FROM Intersection i")})
public class Intersection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "symbol")
    private Integer symbol;
    @Size(max = 256)
    @Column(name = "title")
    private String title;
    @Column(name = "xcoordinate")
    private BigDecimal xcoordinate;
    @Column(name = "ycoordinate")
    private BigDecimal ycoordinate;
    @Size(max = 256)
    @Column(name = "pdf")
    private String pdf;
    @OneToMany(mappedBy = "intersection")
    private List<PedestrianPushButton> pedestrianPushButtonList;
    @OneToMany(mappedBy = "intersection")
    private List<Pole> poleList;
    @OneToMany(mappedBy = "intersection",cascade = CascadeType.ALL)
    private List<Detector> detectorList;
    @OneToMany(mappedBy = "intersection")
    private List<PedestrianDisplay> pedestrianDisplayList;
    @OneToMany(mappedBy = "intersection",cascade = CascadeType.ALL)
    private List<Access> accessList;
    @OneToMany(mappedBy = "intersection")
    private List<SignalHead> signalHeadList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "intersection")
    private TrafficSignalController trafficSignalController;

    public Intersection() {
    }

    public Intersection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSymbol() {
        return symbol;
    }

    public void setSymbol(Integer symbol) {
        this.symbol = symbol;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(BigDecimal xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public BigDecimal getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(BigDecimal ycoordinate) {
        this.ycoordinate = ycoordinate;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public List<PedestrianPushButton> getPedestrianPushButtonList() {
        return pedestrianPushButtonList;
    }

    public void setPedestrianPushButtonList(List<PedestrianPushButton> pedestrianPushButtonList) {
        this.pedestrianPushButtonList = pedestrianPushButtonList;
    }

    public List<Pole> getPoleList() {
        return poleList;
    }

    public void setPoleList(List<Pole> poleList) {
        this.poleList = poleList;
    }

    public List<Detector> getDetectorList() {
        return detectorList;
    }

    public void setDetectorList(List<Detector> detectorList) {
        this.detectorList = detectorList;
    }

    public List<PedestrianDisplay> getPedestrianDisplayList() {
        return pedestrianDisplayList;
    }

    public void setPedestrianDisplayList(List<PedestrianDisplay> pedestrianDisplayList) {
        this.pedestrianDisplayList = pedestrianDisplayList;
    }

    public List<Access> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<Access> accessList) {
        this.accessList = accessList;
    }

    public List<SignalHead> getSignalHeadList() {
        return signalHeadList;
    }

    public void setSignalHeadList(List<SignalHead> signalHeadList) {
        this.signalHeadList = signalHeadList;
    }

    public TrafficSignalController getTrafficSignalController() {
        return trafficSignalController;
    }

    public void setTrafficSignalController(TrafficSignalController trafficSignalController) {
        this.trafficSignalController = trafficSignalController;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intersection)) {
            return false;
        }
        Intersection other = (Intersection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intersections.model.Intersection[ id=" + id + " ]";
    }
    
}
