/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsource.perfanalyzer;

import java.io.Serializable;

/**
 *
 * @author k.shaposhnik
 */
public class ReportItem implements Serializable{
    private String name;
    private String level;
    private String description;
    private boolean isCritical;

    public ReportItem(String name, String level, String description,boolean isCritical) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.isCritical=isCritical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsCritical() {
        return isCritical;
    }

    public void setIsCritical(boolean isCritical) {
        this.isCritical = isCritical;
    }
    
    
     
    
}
