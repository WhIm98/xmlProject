/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import entities.tblCategory;
import entity.TblCategory;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import utilities.DBUtilities;

import utilities.TestUtil;

/**
 *
 * @author HP
 */
public class CategoryDAO  extends BaseDAO<TblCategory>{

    public CategoryDAO() {
    }
    
    private static CategoryDAO instance;
    private static final Object LOCK = new Object();
    public static CategoryDAO getInstance(){
        synchronized (LOCK){
            if(instance == null){
                instance = new CategoryDAO();
            }
        }
        return instance;
    }
    
    public synchronized TblCategory getFirstCategoryByName(String categoryName){
        EntityManager em = DBUtilities.getEntityManager();
        try{
            List<TblCategory> result = em.createNamedQuery("TblCategory.findByCategoryName",TblCategory.class)
                    .setParameter("categoryName", categoryName)
                    .getResultList();
            if(result !=null && !result.isEmpty()){
                return result.get(0);
            }
        }catch(Exception e){
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE,null,e);
        }finally{
            if(em!=null){
                em.close();
            }
        }
        return null;
    }
    
    public synchronized TblCategory saveCategoryWhileCrawling(String categoryName){
        List<TblCategory> listCategory = getAll("TblCategory.findAll");
        TblCategory cate = null;
        double HighPercentage = 0;
        for (TblCategory tblCategory : listCategory) {
            if (categoryName.equals(tblCategory.getCategoryName())) {
                return tblCategory;
            } else {
                double percentage = TestUtil.getMatchingPercentage(categoryName.toLowerCase(), tblCategory.getCategoryName().toLowerCase());
                if (percentage > HighPercentage) {
                    HighPercentage = percentage;
                    cate = tblCategory;
                }
            }
        }
        TblCategory tblCategory = new TblCategory();
        if (HighPercentage < 0.6) {
            tblCategory.setCategoryName(categoryName);
            
            return create(tblCategory);
        }
        return cate;
    }
}
