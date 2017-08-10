package com.intersections.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "trafficsignalcontrollers")
@NamedQueries({
    @NamedQuery(name = "TrafficSignalController.findAll", query = "SELECT t FROM TrafficSignalController t")})
public class TrafficSignalController implements Serializable {

    private static final long serialVersionUID = 1L;
    @GenericGenerator(name = "sopstvenigenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "intersection"))
    @Id
    @GeneratedValue(generator = "sopstvenigenerator")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "manufacturer")
    private String manufacturer;
    @Size(max = 50)
    @Column(name = "model")
    private String model;
    @Column(name = "yearofproduction")
    private Integer yearOfProduction;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Intersection intersection;

    public TrafficSignalController() {
    }

    public TrafficSignalController(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
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
        if (!(object instanceof TrafficSignalController)) {
            return false;
        }
        TrafficSignalController other = (TrafficSignalController) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intersections.model.TrafficSignalController[ id=" + id + " ]";
    }
    
}
