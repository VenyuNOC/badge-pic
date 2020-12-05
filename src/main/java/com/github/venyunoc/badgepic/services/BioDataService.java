/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nathan
 */
public class BioDataService {
    
    private final static Logger logger = LoggerFactory.getLogger(BioDataService.class);
    
    private String firstName;
    private String lastName;
    private String badgeNumber;
    
    public BioDataService() {
        this.firstName = "";
        this.lastName = "";
        this.badgeNumber = "";
    }
    
    public void submitFirstName(String value) {
        logger.debug("First name changed, old: {}, new: {}", this.firstName, value);
        this.firstName = value;
    }
    
    public void submitLastName(String value) {
        logger.debug("Last name changed, old: {}, new: {}", this.lastName, value);
        this.lastName = value;
    }
    
    public void submitBadgeNumber(String value) {
        logger.debug("Badge number changed, old: {}, new: {}", this.badgeNumber, value);
        this.badgeNumber = value;
    }
    
    public String firstName() {
        return this.firstName;
    }
    
    public String lastName() {
        return this.lastName;
    }
    
    public String badgeNumber() {
        return this.badgeNumber;
    }
    
}
