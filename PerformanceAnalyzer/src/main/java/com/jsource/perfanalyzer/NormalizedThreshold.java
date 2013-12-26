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
public enum NormalizedThreshold {

    ONE_QUARTER(0.25), ONE_THIRD(0.33), HALF(0.5), TWO_THIRDS(0.66), THREE_QUARTERS(0.75);

    private final double value;

    private NormalizedThreshold(double value) {
        this.value = value;
    }
    
    public double getValue(){
        return value;
    }

}
