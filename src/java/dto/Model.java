/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class Model implements Serializable{
    protected String brand;
    protected String name;
    protected String category;
    protected float price;
    protected String imageLink;
    protected String productLink;
    protected String detail;

    public Model(String brand, String name, String category, float price, String imageLink, String productLink, String detail) {
        this.brand = brand;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageLink = imageLink;
        this.productLink = productLink;
        this.detail = detail;
    }

    public Model(String name, String category, float price, String imageLink, String productLink) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageLink = imageLink;
        this.productLink = productLink;
    }
    
    

    

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
    
}
