/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "TblCategory", catalog = "XmlDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCategory.findAll", query = "SELECT t FROM TblCategory t")
    ,@NamedQuery(name = "TblCategory.findByCategoryID", query = "SELECT t FROM TblCategory t WHERE t.categoryID = :CategoryID")
    , @NamedQuery(name = "TblCategory.findByCategoryName", query = "SELECT t FROM TblCategory t WHERE t.categoryName = :categoryName")})
public class TblCategory implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "categoryID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;

    private static final long serialVersionUID = 1L;
    
    @Column(name = "categoryName", length = 500)
    private String categoryName;
    @OneToMany(mappedBy = "categoryID")
    private Collection<TblProduct> tblProductCollection;

    public TblCategory() {
    }

    

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @XmlTransient
    public Collection<TblProduct> getTblProductCollection() {
        return tblProductCollection;
    }

    public void setTblProductCollection(Collection<TblProduct> tblProductCollection) {
        this.tblProductCollection = tblProductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCategory)) {
            return false;
        }
        TblCategory other = (TblCategory) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblCategory[ id=" + categoryID + " ]";
    }

    public TblCategory(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    
    
}
