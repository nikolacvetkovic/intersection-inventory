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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "poles")
@NamedQueries({
    @NamedQuery(name = "Pole.findAll", query = "SELECT p FROM Pole p")})
public class Pole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "xcoordinate")
    private BigDecimal xcoordinate;
    @Column(name = "ycoordinate")
    private BigDecimal ycoordinate;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 50)
    @Column(name = "model")
    private String model;
    @Size(max = 50)
    @Column(name = "manufacturer")
    private String manufacturer;
    @OneToMany(mappedBy = "pole")
    private List<PedestrianPushButton> pedestrianPushButtonList;
    @JoinColumn(name = "access", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Access access;
    @JoinColumn(name = "intersection", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Intersection intersection;
    @OneToMany(mappedBy = "pole", cascade = CascadeType.ALL)
    private List<PedestrianDisplay> pedestrianDisplayList;
    @OneToMany(mappedBy = "pole", cascade = CascadeType.ALL)
    private List<SignalHead> signalHeadList;

    public Pole() {
    }

    public Pole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<PedestrianPushButton> getPedestrianPushButtonList() {
        return pedestrianPushButtonList;
    }

    public void setPedestrianPushButtonList(List<PedestrianPushButton> pedestrianPushButtonList) {
        this.pedestrianPushButtonList = pedestrianPushButtonList;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    public List<PedestrianDisplay> getPedestrianDisplayList() {
        return pedestrianDisplayList;
    }

    public void setPedestrianDisplayList(List<PedestrianDisplay> pedestrianDisplayList) {
        this.pedestrianDisplayList = pedestrianDisplayList;
    }

    public List<SignalHead> getSignalHeadList() {
        return signalHeadList;
    }

    public void setSignalHeadList(List<SignalHead> signalHeadList) {
        this.signalHeadList = signalHeadList;
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
        if (!(object instanceof Pole)) {
            return false;
        }
        Pole other = (Pole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intersections.model.Pole[ id=" + id + " ]";
    }
    
}
