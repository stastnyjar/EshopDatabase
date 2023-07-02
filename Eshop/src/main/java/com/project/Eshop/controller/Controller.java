package com.project.Eshop.controller;

import com.project.Eshop.AddGUI;
import com.project.Eshop.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/load-all-available-items")
    public ArrayList<String> loadAllAvailableItems(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", "password");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from products");
            ArrayList<String> productNames = new ArrayList<>();
            while(resultset.next()){
                productNames.add(resultset.getString("name"));
            }
            return productNames;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/load-product-by-ID")
    public Product loadProductByID(
            @RequestParam(value = "ID", required = true) int ID){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", "password");
            Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery(String.format(("select * from Products where partNo = %d"), ID));
            Product product = new Product();
            while(resultset.next()){
                product.setId(resultset.getInt("id"));
                product.setPartNo(resultset.getInt("partNo"));
                product.setName(resultset.getString("name"));
                product.setDescription(resultset.getString("description"));
                product.setForSale(resultset.getBoolean("isForSale"));
                product.setPrice(BigDecimal.valueOf(resultset.getInt("price")));
            }
            return product;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    @GetMapping("/save-item")
    public void saveItem(Product product){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", "password");
            Statement statement = con.createStatement();
            statement.executeUpdate(String.format("insert into products (partNo, name, desc, price, forSale) values (%s, '%s', '%s', %s, %s)", product.getPartNo(), product.getName(), product.getDescription(), product.getPrice(), product.isForSale()));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    @GetMapping("/update-price-by-ID")
    public void updatePriceByID(
            @RequestParam(value = "partNo", required = true) int partNo, @RequestParam(value = "price", required = true) int price){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", "password");
            Statement statement = con.createStatement();
            statement.executeUpdate(String.format("update products set price = %d where partNo = %d", price, partNo));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    @GetMapping("/delete-out-of-sale-items")
    public void deleteOutOfSaleItems(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", "password");
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from products where isForSale = 0");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
