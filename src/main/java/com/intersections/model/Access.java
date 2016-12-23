package com.intersections.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "accesses")
@NamedQueries({
    @NamedQuery(name = "Access.findAll", query = "SELECT a FROM Access a")})
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "symbol")
    private Integer symbol;
    @Size(max = 50)
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "access")
    private List<Pole> poleList;
    @OneToMany(mappedBy = "access")
    private List<Detector> detectorList;
    @JoinColumn(name = "intersection", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Intersection intersection;

    public Access() {
    }

    public Access(Integer id) {
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
        if (!(object instanceof Access)) {
            return false;
        }
        Access other = (Access) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intersections.model.Access[ id=" + id + " ]";
    }
    
}
