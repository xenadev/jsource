package com.jsource.perfanalyzer;

/**
 * @file ClassMetric.java
 * @brief
 * @author Kseniya Shaposhnikova
 * @date Dec 24, 2013 12:03:30 PM
 */
public enum ClassMetric {

    CLASS_NAME("Name"), ATFD("ATFD"), WMC("WMC"), TCC("TCC"), METHOD_SUM_LOC(
	    "method.sum_LOC");

    private String name;

    private ClassMetric(String name) {
	this.name = name;
    }

    public String[] getAllNames() {
	String[] names = new String[] { CLASS_NAME.name, ATFD.name, WMC.name,
		TCC.name, METHOD_SUM_LOC.name };

	return names;
    }

    
    @Override
    public String toString(){
	return name;
    }
}
