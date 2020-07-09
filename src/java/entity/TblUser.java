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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */

@Entity
@Table(name = "TblUser", catalog = "XmlDB", schema = "dbo")
@XmlRootElement
public class TblUser implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(name = "fullName", length = 2147483647)
    private String fullName;
    @Column(name = "password", length = 2147483647)
    private String password;
    @OneToMany(mappedBy = "userId")
    private Collection<TblRecommand> tblRecommandCollection;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public TblUser() {
    }

    @XmlTransient
    public Collection<TblRecommand> getTblRecommandCollection() {
        return tblRecommandCollection;
    }

    public void setTblRecommandCollection(Collection<TblRecommand> tblRecommandCollection) {
        this.tblRecommandCollection = tblRecommandCollection;
    }
    
    
    
}
