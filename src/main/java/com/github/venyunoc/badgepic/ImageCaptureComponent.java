/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic;

import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nathan
 */
public class ImageCaptureComponent extends JPanel {
    
    private final static Logger logger = LoggerFactory.getLogger(ImageCaptureComponent.class);
    
    private final ImageDataService imageDataService;
    private final ImageWriterService fileWriterService;
    private final BioDataService bioDataService;
    private BufferedImage image;
    
    public ImageCaptureComponent(ImageDataService imageDataService, 
                                 ImageWriterService fileWriterService,
                                 BioDataService bioDataService) {
        this.imageDataService = imageDataService;
        this.fileWriterService = fileWriterService;
        this.bioDataService = bioDataService;
        
        this.image = null;
        
        init();
        layoutComponents();
    }
    
    private void init() {
        logger.trace("entering init");
        
        this.imagePanel = new ImagePanel();
        this.captureButton = new JButton("Take Picture");
        this.saveButton = new JButton("Save Image");
        
        logger.trace("created UI components");
        
        this.captureButton.addActionListener((l) -> {
            this.image = this.imageDataService.getImageData();
            imagePanel.setImageAndSignalRedraw(image);
        });
        
        this.saveButton.addActionListener((l) -> {
            if (this.image.getWidth() > 0 && this.image.getHeight() > 0) {
                this.fileWriterService.writeImage(
                        String.format("%s - %s, %s.jpg", 
                                      bioDataService.badgeNumber(),
                                      bioDataService.lastName(),
                                      bioDataService.firstName()), image);
            }
        });
        
        logger.trace("added action listeners to buttons");
        
        logger.trace("leaving init");
    }
    
    private void layoutComponents() {
        logger.trace("inside layoutComponents");
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(imagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup()
                    .addComponent(this.captureButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.saveButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );
        
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(this.imagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(this.captureButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.saveButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
        
        
        logger.trace("leaving layoutComponents");
    }
    
    private ImagePanel imagePanel;
    private JButton captureButton;
    private JButton saveButton;
    
}
