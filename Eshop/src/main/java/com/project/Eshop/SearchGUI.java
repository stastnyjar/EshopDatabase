package com.project.Eshop;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SearchGUI extends JFrame{
    private String password;
    private JComboBox criteriumType;
    private JTextField criteriumField;
    private JButton searchB;
    
    public SearchGUI(String password){
        super("Search");
        this.password = password;
        Container con = getContentPane();
        con.setBackground(Color.WHITE);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        criteriumType = new JComboBox();
        gbc.gridx = 0;
        gbc.gridy = 0;
        con.add(criteriumType, gbc);
        criteriumField = new JTextField();
        gbc.gridx = 1;
        con.add(criteriumField, gbc);
        criteriumField.setPreferredSize(new Dimension(100, 20));
        searchB = new JButton("Search");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        con.add(searchB, gbc);
        criteriumType.addItem("Part number");
        criteriumType.addItem("Name");
        criteriumType.addItem("Price");
        criteriumType.addItem("Minimum price");
        criteriumType.addItem("Maximum price");
        ProductSearchEvent event = new ProductSearchEvent();
        searchB.addActionListener(event);
    }
    public class ProductSearchEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String value = criteriumField.getText();
            HashMap<String, String> properties = new HashMap<>();
            properties.put("Part number", "partNo");
            properties.put("Name", "name");
            properties.put("Price", "price");
            properties.put("Minimum price", "minPrice");
            properties.put("Maximum price", "maxPrice");
            String property = properties.get((String)criteriumType.getSelectedItem());
            try{
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", password);
                Statement statement = con.createStatement();
                ResultSet resultSet;
                if(property.equals("minPrice")){
                    resultSet = statement.executeQuery(String.format("select * from Products where price >= '%s'", value));
                    while(resultSet.next()){
                        System.out.println(resultSet.getString("name"));
                    }
                }else if(property.equals("maxPrice")){
                    resultSet = statement.executeQuery(String.format("select * from Products where price <= '%s'", value));
                    while(resultSet.next()){
                        System.out.println(resultSet.getString("name"));
                    }
                }else if(property.equals("price")){
                    resultSet = statement.executeQuery(String.format("select * from Products where price = '%s'", value));
                    while(resultSet.next()){
                        System.out.println(resultSet.getString("name"));
                    } 
                }else{
                    resultSet = statement.executeQuery(String.format("select * from Products where %s = '%s'", property, value));
                    while(resultSet.next()){
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Description: " + resultSet.getString("description"));
                        System.out.println("Price: " + resultSet.getInt("price"));
                        if(resultSet.getBoolean("isForSale") == false){
                            System.out.println("Currently unavailable");
                        }
                    }
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
