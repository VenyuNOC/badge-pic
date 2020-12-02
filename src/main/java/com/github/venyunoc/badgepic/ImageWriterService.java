/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nathan
 */
public class ImageWriterService {
    
    private static final Logger logger = LoggerFactory.getLogger(ImageWriterService.class);
    
    public final Path folder;
    
    public ImageWriterService(Path folder) {
        this.folder = folder;
    }
    
    public void writeImage(String filename, BufferedImage image) {
        File outputFile = this.folder.resolve(filename).toFile();
        
        try {
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
