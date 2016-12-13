package com.intersections.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "detectors")
@NamedQueries({
    @NamedQuery(name = "Detector.findAll", query = "SELECT d FROM Detector d")})
public class Detector implements Serializable {

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
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "xDimension")
    private BigDecimal xDimension;
    @Column(name = "yDimension")
    private BigDecimal yDimension;
    @Column(name = "position")
    private Integer position;
    @JoinColumn(name = "access", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Access access;
    @JoinColumn(name = "intersection", referencedColumnName = "id")
    @ManyToOne (cascade = CascadeType.ALL)
    private Intersection intersection;

    public Detector() {
    }

    public Detector(Integer id) {
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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
    
    public BigDecimal getxDimension() {
        return xDimension;
    }

    
    public void setxDimension(BigDecimal xDimension) {
        this.xDimension = xDimension;
    }

    public BigDecimal getyDimension() {
        return yDimension;
    }

    public void setyDimension(BigDecimal yDimension) {
        this.yDimension = yDimension;
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
        if (!(object instanceof Detector)) {
            return false;
        }
        Detector other = (Detector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intersections.model.Detector[ id=" + id + " ]";
    }

}
