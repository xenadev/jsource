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
            double[][] methodMetricsNum = MetricUtils.convertMethodsMetricsFromStrToNum(methodMetrics);

            //get values of each metric
            String[] methodName = MetricUtils.getStringColumn(methodMetrics, MethodMetric.METHOD_NAME.getOrderNum());
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

            //iterate over methods and check for disharmonies
            for (int i = 0; i < methodName.length; i++) {
                //check feature envy
                if (DisharmoniesFinder.findFeatureEnvy(atfd[i], laa[i], fdp[i])) {
                    featureEnvyMethods.add(methodName[i]);
                }

                //check brain method
                if (DisharmoniesFinder.findBrainMethod(loc[i], cyclo[i], maxnesting[i], noav[i])) {
                    brainMethods.add(methodName[i]);
                }

                //check intensive coupling
                if (DisharmoniesFinder.findIntensiveCoupling(cint[i], cdisp[i], maxnesting[i])) {
                    intensiveCouplingMethods.add(methodName[i]);
                }

                //check dispersed coupling
                if (DisharmoniesFinder.findDispersedCoupling(cint[i], cdisp[i], maxnesting[i])) {
                    dispersedCouplingMethods.add(methodName[i]);
                }

                //check shotgun surgery
                if (DisharmoniesFinder.findShotgunSurgery(cm[i], cc[i])) {
                    shotgunSurgery.add(methodName[i]);
                }

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
            double[][] classMetricsNum = MetricUtils.convertMethodsMetricsFromStrToNum(classMetrics);

            //get values of each metric
            String[] classNames = MetricUtils.getStringColumn(classMetrics, ClassMetric.CLASS_NAME.getOrderNum());
            double[] atfd = MetricUtils.getDoubleColumn(classMetricsNum, ClassMetric.ATFD.getOrderNum());
            double[] wmc = MetricUtils.getDoubleColumn(classMetricsNum, ClassMetric.WMC.getOrderNum());
            double[] tcc = MetricUtils.getDoubleColumn(classMetricsNum, ClassMetric.TCC.getOrderNum());

            //find God classes
            List<String> godClasses = DisharmoniesFinder.findGodClass(classNames, atfd, wmc, tcc);

            //report about God classes
            for (String item : godClasses) {
                report.add(new ReportItem(item, CLASS_LEVEL, "God Class"));
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
