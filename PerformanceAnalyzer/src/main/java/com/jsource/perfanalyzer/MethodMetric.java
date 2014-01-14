package com.jsource.perfanalyzer;

/**
 * @file MethodMetric.java
 * @brief
 * @author Kseniya Shaposhnikova
 * @date Dec 24, 2013 12:03:14 PM
 */
public enum MethodMetric {

    METHOD_NAME("Name", 0), SCOPE_NAME("scope name", 1), ATFD("ATFD", 2), LAA("LAA", 3), FDP("FDP", 4), LOC("LOC", 5), CYCLO(
            "CYCLO", 6), MAXNESTING("MAXNESTING", 7), NOAV("NOAV", 8), CINT("CINT", 9), CDISP(
                    "CDISP", 10), CM("CM", 11), CC("CC", 12);

    private final String name;
    private final int orderNum;

    private MethodMetric(String name, int orderNum) {
        this.name = name;
        this.orderNum = orderNum;
    }

    public String[] getAllNames() {
        String[] names = new String[]{METHOD_NAME.name, ATFD.name, LAA.name,
            FDP.name, LOC.name, CYCLO.name, MAXNESTING.name, NOAV.name,
            CINT.name, CDISP.name, CM.name, CC.name, SCOPE_NAME.name};

        return names;
    }

    public int getOrderNum() {
        return orderNum;
    }

    @Override
    public String toString() {
        return name;
    }

}
