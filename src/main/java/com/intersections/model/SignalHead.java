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
@Table(name = "signalheads")
@NamedQueries({
    @NamedQuery(name = "SignalHead.findAll", query = "SELECT s FROM SignalHead s")})
public class SignalHead implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "symbol")
    private String symbol;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 50)
    @Column(name = "importance")
    private String importance;
    @Size(max = 50)
    @Column(name = "dimension")
    private String dimension;
    @Size(max = 10)
    @Column(name = "contrasttable")
    private String contrasttable;
    @Size(max = 10)
    @Column(name = "sound")
    private String sound;
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

    public SignalHead() {
    }

    public SignalHead(Integer id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getContrasttable() {
        return contrasttable;
    }

    public void setContrasttable(String contrasttable) {
        this.contrasttable = contrasttable;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
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
        if (!(object instanceof SignalHead)) {
            return false;
        }
        SignalHead other = (SignalHead) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intersections.model.SignalHead[ id=" + id + " ]";
    }
    
}
