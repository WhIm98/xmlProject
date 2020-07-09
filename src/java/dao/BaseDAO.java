/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import utilities.DBUtilities;

/**
 *
 * @author HP
 * @param <T>
 * @param <PK>
 */
public class BaseDAO<T> implements IGenericDAO<T>{
    protected Class<T> entityClass;

    public BaseDAO() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }
    

    @Override
    public List<T> getAll(String namedQuery) {
        EntityManager em = DBUtilities.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            List<T> result = em.createNamedQuery(namedQuery,entityClass).getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE,null,e);
        }finally{
            if(em!=null){
                em.close();
            }
        }
        return null;
    }

    @Override
    public T create(T t) {
        EntityManager em = DBUtilities.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    @Override
    public T update(T t) {
        EntityManager em = DBUtilities.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }
    
    
}
