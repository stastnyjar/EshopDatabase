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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EditGUI extends JFrame{
    private String password;
    private JLabel productIdLabel;
    private JLabel propertyLabel;
    private JLabel newValueLabel;
    private JTextField productIDField;
    private JComboBox propertyCB;
    private JTextField newValueField;
    private JButton saveB;
    
    public EditGUI(String password){
        super("Edit data");
        this.password = password;
        Container con = getContentPane();
        con.setBackground(Color.WHITE);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        productIdLabel = new JLabel("Product ID:");
        gbc.gridy = 0;
        con.add(productIdLabel, gbc);
        propertyLabel = new JLabel("Property:");
        gbc.gridy = 1;
        con.add(propertyLabel, gbc);
        newValueLabel = new JLabel("New value:");
        gbc.gridy = 2;
        con.add(newValueLabel, gbc);
        gbc.gridx = 1;
        productIDField = new JTextField();
        gbc.gridy = 0;
        con.add(productIDField, gbc);
        propertyCB = new JComboBox();
        gbc.gridy = 1;
        con.add(propertyCB, gbc);
        newValueField = new JTextField();
        gbc.gridy = 2;
        con.add(newValueField, gbc);
        saveB = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        con.add(saveB, gbc);
        HHEditEvent event = new HHEditEvent();
        saveB.addActionListener(event);
        propertyCB.addActionListener(event);
        propertyCB.setActionCommand("property");
        propertyCB.addItem("Part number");
        propertyCB.addItem("Name");
        propertyCB.addItem("Description");
        propertyCB.addItem("Price");
        propertyCB.addItem("Is for sale");
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", password);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from products");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public class HHEditEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                if(e.getActionCommand().equals("Save")){
                    String id = productIDField.getText();            
                    HashMap<String, String> properties = new HashMap<>();
                    properties.put("Part number", "partNo");
                    properties.put("Name", "name");
                    properties.put("Description", "desc");
                    properties.put("Price", "price");
                    properties.put("Is for sale", "isForSale");
                    String property = properties.get((String)propertyCB.getSelectedItem());
                    String newValue;
                    newValue = newValueField.getText();
                    try{
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", password);
                        Statement statement = con.createStatement();
                        statement.executeUpdate(String.format("update products set %s = '%s' where id = '%s'", property, newValue, id));
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
