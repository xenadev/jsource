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

    public List<ReportItem> analyzePerformanceForMethods(String[][] methodMetrics) {
        List<ReportItem> report = new ArrayList<ReportItem>();
        if (methodMetrics != null) {
            printMetrics(methodMetrics);
            ReportItem item1 = new ReportItem("getOptions", "method", "Contains Brain method");
            ReportItem item2 = new ReportItem("getValues", "method", "bad method");
            report.add(item1);
            report.add(item2);

        }

        return report;
    }

    public List<ReportItem> analyzePerformanceForClasses(String[][] classMetrics) {
        List<ReportItem> report = new ArrayList<ReportItem>();
        if (classMetrics != null) {
            printMetrics(classMetrics);

            ReportItem item1 = new ReportItem("Application", "class", "God class");
            ReportItem item2 = new ReportItem("ProfileController", "class", "Contains four brain methods");
            report.add(item1);
            report.add(item2);
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
