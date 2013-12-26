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
public enum ThresholdWMC {

    LOW(5), AVERAGE(14), HIGH(31), VERY_HIGH(47);
    private final int value;

    private ThresholdWMC(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
