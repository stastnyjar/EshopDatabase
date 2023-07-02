package com.project.Eshop;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EshopDatabaseApplication {

    public static void main(String[] args) {
        MainPanel gui = new MainPanel();
        gui.setVisible(true);
        gui.pack();
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setLocationRelativeTo(null);
        SpringApplication.run(EshopDatabaseApplication.class, args);
    }
}
