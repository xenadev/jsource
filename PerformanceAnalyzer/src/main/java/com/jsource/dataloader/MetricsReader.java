package com.jsource.dataloader;

/**
 * @file MetricsReader.java
 * @brief
 * @author Kseniya Shaposhnikova
 * @date Dec 23, 2013 11:31:21 AM
 */
public interface MetricsReader {

    /**
     * Reads metric values from file.
     *
     * @param metricNames Metrics names
     * @param filePath Path to file
     * @return Metric values
     */
    public String[][] readMetrics(String[] metricNames, String filePath);

}
