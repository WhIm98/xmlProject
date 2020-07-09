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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TblProduct", catalog = "XmlDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProduct.findAll", query = "SELECT t FROM TblProduct t")
    , @NamedQuery(name = "TblProduct.findById", query = "SELECT t FROM TblProduct t WHERE t.id = :id")
    , @NamedQuery(name = "TblProduct.findByProductName", query = "SELECT t FROM TblProduct t WHERE t.productName = :productName")
    , @NamedQuery(name = "TblProduct.findByPrice", query = "SELECT t FROM TblProduct t WHERE t.price = :price")
    , @NamedQuery(name = "TblProduct.findByImageLink", query = "SELECT t FROM TblProduct t WHERE t.imageLink = :imageLink")
    , @NamedQuery(name = "TblProduct.findByProductLink", query = "SELECT t FROM TblProduct t WHERE t.productLink = :productLink")
    , @NamedQuery(name = "TblProduct.findByNameAndCategoryId", query = "SELECT t FROM TblProduct t WHERE lower(t.productName) LIKE lower(CONCAT('%',:productName,'%')) AND t.categoryID =:categoryID AND t.productLink =:productLink")
})
public class TblProduct implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productName", length = 500)
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price", precision = 53)
    private Double price;
    @Column(name = "imageLink", length = 1000)
    private String imageLink;
    @Column(name = "productLink", length = 100)
    private String productLink;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @ManyToOne
    private TblCategory categoryID;
    
    @OneToMany(mappedBy = "productID")
    private Collection<TblRecommand> tblRecommandCollection;

    public TblProduct() {
    }

    public TblProduct(String productName, Double price, String imageLink, String productLink, TblCategory categoryID) {
        this.productName = productName;
        this.price = price;
        this.imageLink = imageLink;
        this.productLink = productLink;
        this.categoryID = categoryID;
    }
    
    

    public TblProduct(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public TblCategory getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(TblCategory categoryID) {
        this.categoryID = categoryID;
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
        if (!(object instanceof TblProduct)) {
            return false;
        }
        TblProduct other = (TblProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblProduct[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TblRecommand> getTblRecommandCollection() {
        return tblRecommandCollection;
    }

    public void setTblRecommandCollection(Collection<TblRecommand> tblRecommandCollection) {
        this.tblRecommandCollection = tblRecommandCollection;
    }
    
}
