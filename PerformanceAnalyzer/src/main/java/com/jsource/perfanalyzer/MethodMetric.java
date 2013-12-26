package com.jsource.perfanalyzer;

/**
 * @file MethodMetric.java
 * @brief
 * @author Kseniya Shaposhnikova
 * @date Dec 24, 2013 12:03:14 PM
 */
public enum MethodMetric {

    METHOD_NAME("Name", 0), ATFD("ATFD", 1), LAA("LAA", 2), FDP("FDP", 3), LOC("LOC", 4), CYCLO(
            "CYCLO", 5), MAXNESTING("MAXNESTING", 6), NOAV("NOAV", 7), CINT("CINT", 8), CDISP(
                    "CDISP", 9), CM("CM", 10), CC("CC", 11), SCOPE_NAME("scope name", 12);

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
