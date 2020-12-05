/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic.services;

import java.awt.image.BufferedImage;

/**
 *
 * @author nathan
 */
public class ActiveImageChangeEvent {
    public final BufferedImage oldImage;
    public final BufferedImage newImage;
    
    public ActiveImageChangeEvent(BufferedImage old, BufferedImage upd) {
        this.oldImage = old;
        this.newImage = upd;
    }
}
