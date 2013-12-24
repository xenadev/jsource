package com.jsource.perfanalyzer;

/**
 * @file MethodMetric.java
 * @brief
 * @author Kseniya Shaposhnikova
 * @date Dec 24, 2013 12:03:14 PM
 */
public enum MethodMetric {
    METHOD_NAME("Name"), ATFD("ATFD"), LAA("LAA"), FDP("FDP"), LOC("LOC"), CYCLO(
	    "CYCLO"), MAXNESTING("MAXNESTING"), NOAV("NOAV"), CINT("CINT"), CDISP(
	    "CDISP"), CM("CM"), CC("CC"), SCOPE_NAME("scope name");

    private String name;

    private MethodMetric(String name) {
	this.name = name;
    }

    public String[] getAllNames() {
	String[] names = new String[] { METHOD_NAME.name, ATFD.name, LAA.name,
		FDP.name, LOC.name, CYCLO.name, MAXNESTING.name, NOAV.name,
		CINT.name, CDISP.name, CM.name, CC.name, SCOPE_NAME.name };

	return names;
    }
    
    @Override
    public String toString(){
	return name;
    }

}
