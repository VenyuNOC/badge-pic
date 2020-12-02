/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nathan
 */
public class PictureCapture {
    
    private static Logger logger = LoggerFactory.getLogger(PictureCapture.class);
    
    public static void main(String args[]) {
        Config.init();
        
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
        
        
        Path outputFolder = Paths.get(Config.defaultFolder_Windows);
        
        ImageDataService imageDataService = new ImageDataService();
        ImageWriterService imageWriterService = new ImageWriterService(outputFolder);
        BioDataService bioDataService = new BioDataService();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UI(imageDataService, imageWriterService, bioDataService).setVisible(true);
        });
    }
}
