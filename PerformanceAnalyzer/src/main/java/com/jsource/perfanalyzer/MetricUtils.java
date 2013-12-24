package com.jsource.perfanalyzer;

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
    
    public static double[][] convertMethodsMetricsFromStrToNum(String[][] metricsStr){
        double [][] result=new double[metricsStr.length][metricsStr[0].length];
        for (int i=1;i<metricsStr.length-1;i++){
            for (int j=0;j<metricsStr[i].length;j++){
               result[i][j]=Integer.parseInt(metricsStr[i][j]);
            }
        }
        
        return result;
    }
}
