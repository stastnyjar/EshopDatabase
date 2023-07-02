package com.project.Eshop;

import java.math.BigDecimal;

public class Product {
    private int partNo;
    private String name;
    private String description;
    private boolean isForSale;
    private BigDecimal price;
    private int id;
    
    public int getPartNo() {
        return partNo;
    }

    public void setPartNo(int partNo) {
        this.partNo = partNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isForSale() {
        return isForSale;
    }

    public void setForSale(boolean isForSale) {
        this.isForSale = isForSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
