/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nathan
 */
public class ImageDataService {
    private final static Logger logger = LoggerFactory.getLogger(ImageDataService.class.getName());
    
    public BufferedImage getImageData() {
        
        try {
            URL url = new URL(Config.cameraURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.addRequestProperty("Authentication", String.format("Basic %s", authenticationHeader()));
            
            BufferedImage image = ImageIO.read(conn.getInputStream());
            
            return image;
            
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
        throw new RuntimeException("Unable to retrieve image from remote camera. Check connection?");
    }
    
    private String authenticationHeader() {
        String user = Config.cameraUsername;
        String pass = Config.cameraPassword;
        String toEncode = String.format("%s:%s", user, pass);
        
        return new String(Base64.getEncoder().encode(toEncode.getBytes()));
    }
    
}
