package com.jsource.perfanalyzer;

/**
 * @file ClassMetric.java
 * @brief
 * @author Kseniya Shaposhnikova
 * @date Dec 24, 2013 12:03:30 PM
 */
public enum ClassMetric {

    CLASS_NAME("Name", 0), ATFD("ATFD", 1), WMC("WMC", 2), TCC("TCC", 3), METHOD_SUM_LOC(
            "method.sum_LOC", 4);

    private final String name;
    private final int orderNum;

    private ClassMetric(String name, int orderNum) {
        this.name = name;
        this.orderNum = orderNum;
    }

    public String[] getAllNames() {
        String[] names = new String[]{CLASS_NAME.name, ATFD.name, WMC.name,
            TCC.name, METHOD_SUM_LOC.name};

        return names;
    }

    @Override
    public String toString() {
        return name;
    }
}
