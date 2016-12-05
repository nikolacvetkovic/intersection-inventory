package com.intersections.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pedestrianpushbuttons")
@NamedQueries({
    @NamedQuery(name = "PedestrianPushButton.findAll", query = "SELECT p FROM PedestrianPushButton p")})
public class PedestrianPushButton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "symbol")
    private String symbol;
    @Size(max = 10)
    @Column(name = "sound")
    private String sound;
    @Size(max = 10)
    @Column(name = "light")
    private String light;
    @Size(max = 10)
    @Column(name = "locatortone")
    private String locatortone;
    @Size(max = 50)
    @Column(name = "manufacturer")
    private String manufacturer;
    @Size(max = 50)
    @Column(name = "model")
    private String model;
    @JoinColumn(name = "intersection", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Intersection intersection;
    @JoinColumn(name = "pole", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Pole pole;

    public PedestrianPushButton() {
    }

    public PedestrianPushButton(Integer id) {
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

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getLocatortone() {
        return locatortone;
    }

    public void setLocatortone(String locatortone) {
        this.locatortone = locatortone;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    public Pole getPole() {
        return pole;
    }

    public void setPole(Pole pole) {
        this.pole = pole;
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
        if (!(object instanceof PedestrianPushButton)) {
            return false;
        }
        PedestrianPushButton other = (PedestrianPushButton) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intersections.model.PedestrianPushButton[ id=" + id + " ]";
    }
    
}
