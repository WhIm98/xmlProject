/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.TblSurvey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilities.DBUtilities;

/**
 *
 * @author HP
 */
public class XmlDAO {

    public String getHomeRecommed() throws SQLException, Exception {
        Connection con = null;
        PreparedStatement preStm = null;
        String result = "";
        try {
            con = DBUtilities.getMyConnection();
            if (con != null) {
                String sql = "select CAST(( select p.productName, p.price, p.imageLink, p.productLink, categoryName"
                        + " from TblProduct p, TblCategory c"
                        + " where p.categoryID = c.categoryID and p.price > '0'"
                        + " for XML Path ('product'), Root('products')) as NVARCHAR(max)) AS XmlData";
                preStm = con.prepareStatement(sql);
                try (ResultSet rs = preStm.executeQuery()) {
                    if (rs.next()) {
                        result += rs.getString("XmlData");
                    }
                }
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public String getListCategory() throws SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String result = "";
        try {
            con = DBUtilities.getMyConnection();
            if (con != null) {
                String sql = "select cast((select distinct c.categoryID, c.categoryName from TblCategory c, TblProduct p where c.categoryID = p.categoryID"
                        + " for XML Path('category'), Root('categories')) as NVARCHAR(max)) as XmlData";
                stm = con.prepareStatement(sql);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        result += rs.getString("XmlData");
                    }
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public String getListProductByCategory(int id) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String result = "";
        try {
            con = DBUtilities.getMyConnection();
            if (con != null) {
                String sql = "select cast((select p.ID, p.productName, p.price, p.imageLink, p.productLink, categoryName"
                        + " from TblProduct p, TblCategory c"
                        + " where p.categoryID = c.categoryID and c.categoryID = ?"
                        + " for XML Path('product'), Root('products')) as NVARCHAR(max) ) AS XmlData";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        result += rs.getString("XmlData");
                    }
                    if (result.equals("null")) {
                        result = "No Result!!!";
                    }
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public String searchProduct(String search) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String result = "";
        try {
            con = DBUtilities.getMyConnection();
            if (con != null) {
                String sql = "select cast((select p.ID, p.productName, p.price, p.imageLink, p.productLink, categoryName"
                        + " from TblProduct p, TblCategory c"
                        + " where p.categoryID = c.categoryID and p.productName LIKE ?"
                        + " for XML Path('product'), Root('products')) as NVARCHAR(max) ) AS XmlData";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        result += rs.getString("XmlData");
                    }
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public int getCountProduct(String name) throws SQLException, Exception {
        int count = 0;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtilities.getMyConnection();
            if (con != null) {
                String sql = "select count(productName)"
                        + " from TblProduct"
                        + " where productName IN (SELECT productName FROM TblProduct WHERE categoryID = '3' OR \n"
                        + "categoryID = '6' OR categoryID = '8' OR categoryID = '7') AND productName LIKE ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");

                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt(1);
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return count;
    }

    public boolean insertSurvey(TblSurvey survey) throws SQLException, Exception {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.getMyConnection();
            if (con != null) {
                String sql = "INSERT INTO TblSurvey(brand, count) VALUES(?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, survey.getBrand());
                stm.setInt(2, survey.getCount());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public String getSurvey() throws SQLException, Exception {
//        List<TblSurvey> list = new ArrayList<>();
//        TblSurvey survey = null;
        Connection con = null;
        PreparedStatement stm = null;
        String brand = "";
        try {
            con = DBUtilities.getMyConnection();
            if (con != null) {
                String sql = "select distinct cast ((select distinct brand, count from TblSurvey for XML Path('product'), Root('brands')) as NVARCHAR(max) ) AS XmlData";
                stm = con.prepareStatement(sql);
                ResultSet result = stm.executeQuery();
//                while (result.next()) {
//                    String brand = result.getString("brand");
//                    int count = result.getInt("count");
//                    survey = new TblSurvey(brand, count);
//                    list.add(survey);
//                }
                if(result.next()){
                    brand += result.getString("XmlData");
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return brand;
    }
}
