package com.jsource.projectsmanager;

import com.jsource.perfanalyzer.ReportItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @file	Project.java
 * @brief
 * @author	Kseniya Shaposhnikova
 * @date	Dec 24, 2013 11:34:08 AM
 */
public class Project implements Serializable {

    private List<ReportItem> report;
    private String[][] methodMetrics;
    private String[][] classMetrics;
    private String projectFilePath;

    public Project(String projectFilePath) {
        this.projectFilePath = projectFilePath;
        report = new ArrayList<ReportItem>();

    }

    public String[][] getMethodMetrics() {
        return methodMetrics;
    }

    public String[][] getClassMetrics() {
        return classMetrics;
    }

    public void setMethodMetrics(String[][] methodMetrics) {
        this.methodMetrics = methodMetrics;
    }

    public List<ReportItem> getReport() {
        return report;
    }

    public void setClassMetrics(String[][] classMetrics) {
        this.classMetrics = classMetrics;
    }

    public void clear() {
        report.clear();
        methodMetrics = null;
        classMetrics = null;
        projectFilePath = ProjectConstants.DEFAULT_PROJECT_PATH;
    }

}
