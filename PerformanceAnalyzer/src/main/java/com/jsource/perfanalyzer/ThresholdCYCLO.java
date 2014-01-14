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
public enum ThresholdCYCLO {

    LOW(5), AVERAGE(10), HIGH(20), VERY_HIGH(50);
    private final int value;

    private ThresholdCYCLO(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
