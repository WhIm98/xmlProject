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
    @NamedQuery(name = "TblSurvey_1.findAll", query = "SELECT t FROM TblSurvey_1 t")
    , @NamedQuery(name = "TblSurvey_1.findByBrandID", query = "SELECT t FROM TblSurvey_1 t WHERE t.brandID = :brandID")
    , @NamedQuery(name = "TblSurvey_1.findByBrand", query = "SELECT t FROM TblSurvey_1 t WHERE t.brand = :brand")
    , @NamedQuery(name = "TblSurvey_1.findByCount", query = "SELECT t FROM TblSurvey_1 t WHERE t.count = :count")})
public class TblSurvey_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "brandID", nullable = false)
    private Integer brandID;
    @Column(name = "brand", length = 50)
    private String brand;
    @Column(name = "count")
    private Integer count;

    public TblSurvey_1() {
    }

    public TblSurvey_1(Integer brandID) {
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
        if (!(object instanceof TblSurvey_1)) {
            return false;
        }
        TblSurvey_1 other = (TblSurvey_1) object;
        if ((this.brandID == null && other.brandID != null) || (this.brandID != null && !this.brandID.equals(other.brandID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblSurvey_1[ brandID=" + brandID + " ]";
    }
    
}
