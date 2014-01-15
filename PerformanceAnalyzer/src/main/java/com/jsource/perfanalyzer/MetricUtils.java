package com.jsource.perfanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @file MetricUtils.java
 * @brief
 * @author Kseniya Shaposhnikova
 * @date Dec 24, 2013 12:26:39 PM
 */
public class MetricUtils {

    private static String[] getMetricNames(ClassMetric[] metrics) {
        String[] result = new String[metrics.length];
        for (int i = 0; i < metrics.length; i++) {
            result[i] = metrics.toString();
        }
        return result;
    }

    private static String[] getMetricNames(MethodMetric[] metrics) {
        String[] result = new String[metrics.length];
        for (int i = 0; i < metrics.length; i++) {
            result[i] = metrics.toString();
        }
        return result;
    }

    public static String[] getMethodMetricNames() {
        String[] result = null;
        MethodMetric[] metricEnumValues = MethodMetric.values();
        result = getMetricNames(metricEnumValues);

        return result;
    }

    public static String[] getClassMetricNames() {
        String[] result = null;
        ClassMetric[] metricEnumValues = ClassMetric.values();
        result = getMetricNames(metricEnumValues);

        return result;
    }

    public static double[][] convertMetricsFromStrToNum(String[][] metricsStr) {
        double[][] result = new double[metricsStr.length][metricsStr[0].length];
        for (int i = 0; i < metricsStr.length - 1; i++) {
            for (int j = 1; j < metricsStr[i].length; j++) {
                try {
                    result[i][j] = Double.parseDouble(metricsStr[i][j]);
                } catch (NumberFormatException ex) {
                    System.out.println("Cannot convert! " + metricsStr[i][j]);
                    result[i][j] = -1;
                }
            }
        }

        return result;
    }

    public static double[] getDoubleColumn(double[][] array, int index) {
        double[] column = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            column[i] = array[i][index];
        }

        return column;

    }

    public static String[] getStringColumn(String[][] array, int index) {
        String[] column = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            column[i] = array[i][index];
        }

        return column;
    }

    public static LinkedHashMap sortHashMapByValuesD(HashMap<Integer, Integer> passedMap) {
        List<Integer> mapKeys = new ArrayList(passedMap.keySet());
        List<Integer> mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);

        Collections.sort(mapKeys);

        Collections.reverse(mapValues);
        Collections.reverse(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                int comp1 = passedMap.get(key);
                int comp2 = val.intValue();

                if (comp1 == comp2) {
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put(key, val);
                    break;
                }

            }

        }
        return sortedMap;
    }
}
