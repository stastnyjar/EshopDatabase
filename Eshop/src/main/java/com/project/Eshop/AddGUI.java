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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddGUI extends JFrame{
    private String password;
    private JLabel partNoLabel;
    private JLabel nameLabel;
    private JLabel descLabel;
    private JLabel priceLabel;
    private JTextField partNoField;
    private JTextField nameField;
    private JTextField descField;
    private JTextField priceField;
    private JCheckBox notForSaleBox;
    private JButton saveB;
    
    public AddGUI(String password){
        super("Products database");
        this.password = password;
        Container con = getContentPane();
        con.setBackground(Color.WHITE);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        partNoLabel = new JLabel("Part number:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        con.add(partNoLabel, gbc);
        partNoField = new JTextField();
        gbc.gridx = 1;
        con.add(partNoField, gbc);
        nameLabel = new JLabel("Product name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        con.add(nameLabel, gbc);
        nameField = new JTextField();
        gbc.gridx = 1;
        con.add(nameField, gbc);
        descLabel = new JLabel("Description:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        con.add(descLabel, gbc);
        descField = new JTextField();
        gbc.gridx = 1;
        con.add(descField, gbc);
        priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        con.add(priceLabel, gbc);
        priceField = new JTextField();
        gbc.gridx = 1;
        con.add(priceField, gbc);
        gbc.gridy++;
        notForSaleBox = new JCheckBox("Not for sale");
        gbc.gridx = 0;
        gbc.gridy = 5;
        con.add(notForSaleBox, gbc);
        saveB = new JButton("Save");
        gbc.gridx = 1;
        con.add(saveB, gbc);
        HHCreationEvent event = new HHCreationEvent();
        saveB.addActionListener(event);
    }
    public class HHCreationEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int partNo = Integer.parseInt(partNoField.getText());
            String name = nameField.getText();
            String desc = descField.getText();
            int price = Integer.parseInt(priceField.getText());
            boolean forSale = !notForSaleBox.isSelected();
            
            try{
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "engeto", password);
                Statement statement = con.createStatement();
                statement.executeUpdate(String.format("insert into products (partNo, name, description, price, isForSale) values (%d, '%s', '%s', %d, %b)", partNo, name, desc, price, forSale));

            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
