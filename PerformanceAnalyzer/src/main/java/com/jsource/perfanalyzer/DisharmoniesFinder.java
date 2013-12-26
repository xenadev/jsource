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
    public static List<String> findGodClass(String[] classNames, double[] atfd, double[] wmc, double[] tcc) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < classNames.length; i++) {

            if ((atfd[i] > (double) SemanticThreshold.FEW.getValue())
                    && (wmc[i] >= ThresholdWMC.VERY_HIGH.getValue())
                    && (tcc[i] < NormalizedThreshold.ONE_THIRD.getValue())) {
                result.add(classNames[i]);
            } else {
                System.out.println(classNames[i] + " Not a god class");
            }

        }

        return result;
    }

    public static boolean findFeatureEnvy(double atfd, double laa, double fdp) {
        boolean result = false;

        if ((atfd > SemanticThreshold.FEW.getValue()) && (laa < NormalizedThreshold.ONE_THIRD.getValue())
                && (fdp <= SemanticThreshold.FEW.getValue())) {
            result = true;
        }

        return result;
    }

    public static boolean findBrainMethod(double loc, double cyclo, double maxnesting, double noav) {
        boolean result = false;

        return result;
    }

    public static boolean findIntensiveCoupling(double cint, double cdisp, double maxnesting) {
        boolean result = false;

        if ((maxnesting > SemanticThreshold.ONE_SHALLOW.getValue())
                && ((cint > SemanticThreshold.SHORT_MEM_CAPACITY_MAX.getValue() && cdisp < NormalizedThreshold.HALF.getValue())
                || (cint > SemanticThreshold.FEW.getValue() && cdisp < NormalizedThreshold.ONE_QUARTER.getValue()))) {

            result = true;
        }

        return result;
    }

    public static boolean findDispersedCoupling(double cint, double cdisp, double maxnesting) {
        boolean result = false;

        if ((maxnesting > SemanticThreshold.ONE_SHALLOW.getValue())
                && ((cint > SemanticThreshold.SHORT_MEM_CAPACITY_MAX.getValue()) && (cdisp >= NormalizedThreshold.HALF.getValue()))) {

            result = true;
        }
        return result;
    }

    public static boolean findShotgunSurgery(double cm, double cc) {
        boolean result = false;

        if (cm > SemanticThreshold.SHORT_MEM_CAPACITY_MAX.getValue() && cc > SemanticThreshold.SEVERAL.getValue()) {
            result = true;
        }

        return result;
    }

}
