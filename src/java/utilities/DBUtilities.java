/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author HP
 */
public class DBUtilities {

    public DBUtilities() {
    }
    
    private static EntityManagerFactory emf;
    private static final Object LOCK = new Object();
    
    public static EntityManager getEntityManager(){
        synchronized(LOCK){
            if(emf == null){
                try {
                    emf = Persistence.createEntityManagerFactory("ProjectXMLPU");
                } catch (Exception e) {
                    Logger.getLogger(DBUtilities.class.getName());
                    e.printStackTrace();
                }
            }
        }
        return emf.createEntityManager();
    }
    
    public static Connection getMyConnection() throws Exception {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=XmlDB", "sb", "123456");
        return conn;
    }
    
}
