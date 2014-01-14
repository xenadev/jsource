/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jsource.perfanalyzer;

/**
 *
 * @author TOSHIBA
 */
public enum ThresholdClassLOC {
    LOW(28), AVERAGE(70), HIGH(130), VERY_HIGH(195);
    private final int value;

    private ThresholdClassLOC(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
