/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsource.perfanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public class DisharmoniesFinder {

    /**
     * Finds classes with "God class" disharmony.
     *
     * @param classNames
     * @param atfd
     * @param wmc
     * @param tcc
     * @return
     */
    public static boolean isGodClass(double atfd, double wmc, double tcc) {
        boolean result = false;

        if ((atfd > SemanticThreshold.FEW.getValue())
                && (wmc >= ThresholdWMC.VERY_HIGH.getValue())
                && (tcc < NormalizedThreshold.ONE_THIRD.getValue())) {
            result = true;
        }

        return result;
    }

    public static boolean isBrainClass(double loc, double wmc, double tcc) {
        boolean result = false;

        boolean isVeryLarge = false;
        if (loc >= ThresholdClassLOC.VERY_HIGH.getValue()) {
            isVeryLarge = true;
        }

        boolean isLargeAndComplex = false;
        if (loc >= (2 * ThresholdClassLOC.VERY_HIGH.getValue())
                && wmc >= (2 * ThresholdWMC.VERY_HIGH.getValue())) {
            isLargeAndComplex = true;
        }

        boolean isComplexNonCoh = false;
        if (wmc >= ThresholdWMC.VERY_HIGH.getValue()
                && tcc < NormalizedThreshold.HALF.getValue()) {
            isComplexNonCoh = true;
        }

        if ((isVeryLarge || isLargeAndComplex) && isComplexNonCoh) {
            result = true;
        }

        return result;
    }

    public static boolean isFeatureEnvy(double atfd, double laa, double fdp) {
        boolean result = false;

        if ((atfd > SemanticThreshold.FEW.getValue()) && (laa < NormalizedThreshold.ONE_THIRD.getValue())
                && (fdp <= SemanticThreshold.FEW.getValue())) {
            result = true;
        }

        return result;
    }

    public static boolean isBrainMethod(double loc, double cyclo, double maxnesting, double noav) {
        boolean result = false;

        if ((loc > ThresholdClassLOC.HIGH.getValue() / 2) && (cyclo >= ThresholdCYCLO.HIGH.getValue()) && (maxnesting >= SemanticThreshold.SEVERAL.getValue())
                && (noav > SemanticThreshold.SHORT_MEM_CAPACITY_MAX.getValue())) {
            result = true;
        }

        return result;
    }

    public static boolean isIntensiveCoupling(double cint, double cdisp, double maxnesting) {
        boolean result = false;

        if ((maxnesting > SemanticThreshold.ONE_SHALLOW.getValue())
                && ((cint > SemanticThreshold.SHORT_MEM_CAPACITY_MAX.getValue() && cdisp < NormalizedThreshold.HALF.getValue())
                || (cint > SemanticThreshold.FEW.getValue() && cdisp < NormalizedThreshold.ONE_QUARTER.getValue()))) {

            result = true;
        }

        return result;
    }

    public static boolean isDispersedCoupling(double cint, double cdisp, double maxnesting) {
        boolean result = false;

        if ((maxnesting > SemanticThreshold.ONE_SHALLOW.getValue())
                && ((cint > SemanticThreshold.SHORT_MEM_CAPACITY_MAX.getValue()) && (cdisp >= NormalizedThreshold.HALF.getValue()))) {

            result = true;
        }
        return result;
    }

    public static boolean isShotgunSurgery(double cm, double cc) {
        boolean result = false;

        if (cm > SemanticThreshold.SHORT_MEM_CAPACITY_MAX.getValue() && cc > SemanticThreshold.SEVERAL.getValue()) {
            result = true;
        }

        return result;
    }

}
