/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */

@Entity
@Table(name = "TblRecommand", catalog = "XmlDB", schema = "dbo")
@XmlRootElement
public class TblRecommand implements Serializable{

    

    @JoinColumn(name = "productID", referencedColumnName = "ID")
    @ManyToOne
    private TblProduct productID;
    @Id
    @Basic(optional = false)
    @Column(name = "recommandId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommandId;
    
    @Column(name = "productPoint")
    private Double productPoint;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private TblProduct productId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private TblUser userId;
    

    public Long getRecommandId() {
        return recommandId;
    }

    public void setRecommandId(Long recommandId) {
        this.recommandId = recommandId;
    }

    public Double getProductPoint() {
        return productPoint;
    }

    public void setProductPoint(Double productPoint) {
        this.productPoint = productPoint;
    }

    public TblProduct getProductId() {
        return productId;
    }

    public void setProductId(TblProduct productId) {
        this.productId = productId;
    }

    

    public TblRecommand() {
    }

    public TblProduct getProductID() {
        return productID;
    }

    public void setProductID(TblProduct productID) {
        this.productID = productID;
    }

    public TblUser getUserId() {
        return userId;
    }

    public void setUserId(TblUser userId) {
        this.userId = userId;
    }
    
    
}
