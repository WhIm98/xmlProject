/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "TblSurvey", catalog = "XmlDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSurvey.findAll", query = "SELECT t FROM TblSurvey t")
    , @NamedQuery(name = "TblSurvey.findByBrandID", query = "SELECT t FROM TblSurvey t WHERE t.brandID = :brandID")
    , @NamedQuery(name = "TblSurvey.findByBrand", query = "SELECT t FROM TblSurvey t WHERE t.brand = :brand")
    , @NamedQuery(name = "TblSurvey.findByCount", query = "SELECT t FROM TblSurvey t WHERE t.count = :count")})
public class TblSurvey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "brandID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandID;
    @Column(name = "brand", length = 50)
    private String brand;
    @Column(name = "count")
    private Integer count;

    public TblSurvey() {
    }

    public TblSurvey(String brand, Integer count) {
        this.brand = brand;
        this.count = count;
    }

    
    public TblSurvey(Integer brandID) {
        this.brandID = brandID;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandID != null ? brandID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSurvey)) {
            return false;
        }
        TblSurvey other = (TblSurvey) object;
        if ((this.brandID == null && other.brandID != null) || (this.brandID != null && !this.brandID.equals(other.brandID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "contants.TblSurvey[ brandID=" + brandID + " ]";
    }
    
}
