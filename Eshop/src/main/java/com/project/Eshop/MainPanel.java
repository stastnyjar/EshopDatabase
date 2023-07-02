package com.project.Eshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainPanel extends JFrame{
    private JTextField passwordF;
    private JButton addB;
    private JButton editB;
    private JButton searchB;
    
    public MainPanel(){
        super("Eshop Database");
        Container con = getContentPane();
        con.setPreferredSize(new Dimension(400, 320));
        con.setBackground(Color.WHITE);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        passwordF = new JTextField();
        con.add(passwordF, gbc);
        gbc.gridy++;
        addB = new JButton("Add product");
        con.add(addB, gbc);
        gbc.gridy++;
        editB = new JButton("Edit product");
        con.add(editB, gbc);
        gbc.gridy++;
        searchB = new JButton("Search database");
        con.add(searchB, gbc);
        gbc.gridy++;
        MainPanelEvent event = new MainPanelEvent();
        addB.addActionListener(event);
        editB.addActionListener(event);
        searchB.addActionListener(event);
    }
    public String getPassword(){
        return passwordF.getText();
    }
    public class MainPanelEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "engeto", passwordF.getText());
                if(e.getActionCommand().equals("Add product")){
                    AddGUI gui = new AddGUI(passwordF.getText());
                    gui.setVisible(true);
                    gui.pack();
                    gui.setLocationRelativeTo(null);
                }else if(e.getActionCommand().equals("Edit product")){
                    EditGUI gui = new EditGUI(passwordF.getText());
                    gui.setVisible(true);
                    gui.pack();
                    gui.setLocationRelativeTo(null);
                }else if(e.getActionCommand().equals("Search database")){
                    SearchGUI gui = new SearchGUI(passwordF.getText());
                    gui.setVisible(true);
                    gui.pack();
                    gui.setLocationRelativeTo(null);
                }
            }catch(Exception ex){
                if(ex instanceof SQLException){
                    System.out.println("Wrong password!");
                }else{
                    ex.printStackTrace();
                }
            }
        }
    }
}
