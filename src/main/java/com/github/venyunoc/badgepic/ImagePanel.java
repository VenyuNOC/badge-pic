/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nathan
 */
public class ImagePanel extends JPanel {
    
    private final static Logger logger = LoggerFactory.getLogger(ImagePanel.class);
    
    private BufferedImage image = null;
        
    public ImagePanel() {
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
        
    }
    
    public void setImageAndSignalRedraw(BufferedImage image) {
        logger.trace("entering setImageAndSignalRedraw");
        logger.debug("set new image: {}x{}, {}", image.getWidth(), image.getHeight(), image.getColorModel().toString());
        
        this.image = image;
        
        this.invalidate();
        logger.trace("leaving setImageAndSignalRedraw");
    }
    
}
