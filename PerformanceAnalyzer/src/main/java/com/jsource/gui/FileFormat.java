/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsource.gui;

/**
 *
 * @author k.shaposhnik
 */
public enum FileFormat {

    EXCEL(0), PROJECT(1);
    private final int formatCode;

    private FileFormat(int formatCode) {
        this.formatCode = formatCode;
    }
    
    public int getValue(){
        return formatCode;
    }
}
