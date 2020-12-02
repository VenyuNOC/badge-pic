/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nathan
 */
public class Config {
    private final static Logger logger = LoggerFactory.getLogger(Config.class.getName());
    
    protected static void init() {
        Properties config = new Properties();
        InputStream is = Config.class.getClassLoader().getResourceAsStream("app.properties");
        try {
            config.load(is);
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
        
        cameraUsername = config.getProperty("Username");
        cameraPassword = config.getProperty("Password");
        cameraURL = config.getProperty("Camera_URL");
        defaultFolder_Windows = config.getProperty("OutputFolder_Windows");
    }
    
    public static String cameraUsername;
    public static String cameraPassword;
    public static String cameraURL;
    public static String defaultFolder_Windows;
}
