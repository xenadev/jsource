/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsource.perfanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author k.shaposhnik
 */
public class PerformanceAnalyzer {

    private final String METHOD_LEVEL = "method";
    private final String CLASS_LEVEL = "class";

    public List<ReportItem> analyzePerformanceForMethods(String[][] methodMetrics) {
        List<ReportItem> report = new ArrayList<ReportItem>();
        if (methodMetrics != null) {
            //convert metrics to doubles
            double[][] methodMetricsNum = MetricUtils.convertMetricsFromStrToNum(methodMetrics);

            //get values of each metric
            String[] methodName = MetricUtils.getStringColumn(methodMetrics, MethodMetric.METHOD_NAME.getOrderNum());
            String[] scopeName = MetricUtils.getStringColumn(methodMetrics, MethodMetric.SCOPE_NAME.getOrderNum());
            double[] atfd = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.ATFD.getOrderNum());
            double[] laa = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.LAA.getOrderNum());
            double[] fdp = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.FDP.getOrderNum());
            double[] loc = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.LOC.getOrderNum());
            double[] cyclo = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CYCLO.getOrderNum());
            double[] maxnesting = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.MAXNESTING.getOrderNum());
            double[] noav = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.NOAV.getOrderNum());
            double[] cint = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CINT.getOrderNum());
            double[] cdisp = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CDISP.getOrderNum());
            double[] cm = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CM.getOrderNum());
            double[] cc = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CC.getOrderNum());

            List<String> featureEnvyMethods = new ArrayList<String>();
            List<String> brainMethods = new ArrayList<String>();
            List<String> intensiveCouplingMethods = new ArrayList<String>();
            List<String> dispersedCouplingMethods = new ArrayList<String>();
            List<String> shotgunSurgery = new ArrayList<String>();

            int[] dishNumber = new int[methodName.length];
            //iterate over methods and check for disharmonies
            for (int i = 0; i < methodName.length; i++) {
                int foundDisharmoniesCounter = 0;
                //check feature envy
                if (DisharmoniesFinder.isFeatureEnvy(atfd[i], laa[i], fdp[i])) {
                    featureEnvyMethods.add(scopeName[i] + "." + methodName[i]);
                    foundDisharmoniesCounter++;
                }

                //check brain method
                if (DisharmoniesFinder.isBrainMethod(loc[i], cyclo[i], maxnesting[i], noav[i])) {
                    brainMethods.add(scopeName[i] + "." + methodName[i]);
                    foundDisharmoniesCounter++;
                }

                //check intensive coupling
                if (DisharmoniesFinder.isIntensiveCoupling(cint[i], cdisp[i], maxnesting[i])) {
                    intensiveCouplingMethods.add(scopeName[i] + "." + methodName[i]);
                    foundDisharmoniesCounter++;
                }

                //check dispersed coupling
                if (DisharmoniesFinder.isDispersedCoupling(cint[i], cdisp[i], maxnesting[i])) {
                    dispersedCouplingMethods.add(scopeName[i] + "." + methodName[i]);
                    foundDisharmoniesCounter++;
                }

                //check shotgun surgery
                if (DisharmoniesFinder.isShotgunSurgery(cm[i], cc[i])) {
                    shotgunSurgery.add(scopeName[i] + "." + methodName[i]);
                    foundDisharmoniesCounter++;
                }
                dishNumber[i] = foundDisharmoniesCounter;

            }

            //add feature envy to report
            for (String item : featureEnvyMethods) {
                report.add(new ReportItem(item, METHOD_LEVEL, "Feature envy"));
            }

            //add brain methods to report
            for (String item : brainMethods) {
                report.add(new ReportItem(item, METHOD_LEVEL, "Brain method"));
            }

            //add intensive coupling to report
            for (String item : intensiveCouplingMethods) {
                report.add(new ReportItem(item, METHOD_LEVEL, "Intensive coupling"));
            }

            //add dispersed coupling to report
            for (String item : dispersedCouplingMethods) {
                report.add(new ReportItem(item, METHOD_LEVEL, "Dispersed coupling"));
            }

            //add shotgun surgery to report
            for (String item : shotgunSurgery) {
                report.add(new ReportItem(item, METHOD_LEVEL, "Shotgun surgery"));
            }

        }

        return report;
    }

    public List<ReportItem> analyzePerformanceForClasses(String[][] classMetrics) {
        List<ReportItem> report = new ArrayList<ReportItem>();
        if (classMetrics != null) {
            //convert metrics to doubles
            double[][] classMetricsNum = MetricUtils.convertMetricsFromStrToNum(classMetrics);

            //get values of each metric
            String[] classNames = MetricUtils.getStringColumn(classMetrics, ClassMetric.CLASS_NAME.getOrderNum());
            double[] atfd = MetricUtils.getDoubleColumn(classMetricsNum, ClassMetric.ATFD.getOrderNum());
            double[] wmc = MetricUtils.getDoubleColumn(classMetricsNum, ClassMetric.WMC.getOrderNum());
            double[] tcc = MetricUtils.getDoubleColumn(classMetricsNum, ClassMetric.TCC.getOrderNum());
            double[] methSumLOC = MetricUtils.getDoubleColumn(classMetricsNum, ClassMetric.METHOD_SUM_LOC.getOrderNum());

            List<String> godClasses = new ArrayList<String>();
            List<String> brainClasses = new ArrayList<String>();

            int[] dishNumber = new int[classNames.length];

            //iterate over classes and check for disharmonies
            for (int i = 0; i < classNames.length; i++) {
                int foundDisharmoniesCounter = 0;

                //check god class
                if (DisharmoniesFinder.isGodClass(atfd[i], wmc[i], tcc[i])) {
                    godClasses.add(classNames[i]);
                    foundDisharmoniesCounter++;
                }

                //check brain class
                if (DisharmoniesFinder.isBrainClass(methSumLOC[i], wmc[i], tcc[i])) {
                    brainClasses.add(classNames[i]);
                    foundDisharmoniesCounter++;
                }

                dishNumber[i] = foundDisharmoniesCounter;
            }

            //report about God classes
            for (String item : godClasses) {
                report.add(new ReportItem(item, CLASS_LEVEL, "God Class"));
            }

            //report about Brain class
            for (String item : brainClasses) {
                report.add(new ReportItem(item, CLASS_LEVEL, "Brain Class"));
            }

        }

        return report;
    }

    private void printMetrics(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("\n");

        }

    }
}
