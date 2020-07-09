/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblCategory;
import entity.TblProduct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import utilities.DBUtilities;

/**
 *
 * @author HP
 */
public class ProductDAO extends BaseDAO<TblProduct>{

    public ProductDAO(){
        
    }
    private static ProductDAO instance;
    private static final Object LOCK = new Object();
    
    public static ProductDAO getInstance(){
        synchronized (LOCK){
            if(instance == null){ 
                instance = new ProductDAO();
            }
        }
        return instance;
    }

    public TblProduct getProduct(String productName, Long categoryId, String domain){
        EntityManager em = DBUtilities.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            TblCategory cate = new TblCategory(categoryId);
//            CategoryDAO dao = new CategoryDAO();
//            cate = dao.getFirstCategoryByName(productName)
            List<TblProduct> result = em.createNamedQuery("TblProduct.findByNameAndCategoryId", TblProduct.class)
                    .setParameter("productName", productName)
                    .setParameter("categoryID", cate)
                    .setParameter("productLink", domain)
                    .getResultList();
            transaction.commit();
            if(result!=null && !result.isEmpty()){
                return result.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
    
    public synchronized  void saveProductWhenCrawling(TblProduct product){
        TblProduct existedProduct = getProduct(product.getProductName(), product.getCategoryID().getCategoryID(), product.getProductLink());
        TblProduct result;
        if(existedProduct == null){
            result = create(product);
        }else{
            result = update(product);
        }
    }
    
    public synchronized  double pointForCheapProduct(TblProduct product){
        double point  = 0;
        if(product.getPrice() > 0 && product.getPrice() < 500000){
            point +=1;
        }
        return point;
    }
}
