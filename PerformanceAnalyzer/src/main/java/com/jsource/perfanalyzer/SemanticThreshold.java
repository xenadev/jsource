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
public enum SemanticThreshold {

    NONE(0), ONE_SHALLOW(1), TWO(2), THREE(3), FEW(4), SEVERAL(5), SHORT_MEM_CAPACITY_MIN(7), SHORT_MEM_CAPACITY_MAX(8);
    private final int value;

    private SemanticThreshold(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
