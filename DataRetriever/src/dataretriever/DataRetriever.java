/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataretriever;

/**
 *
 * @author TOSHIBA
 */
public class DataRetriever {

    public static String metricsFile = null;
    public static String profilingFile = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        metricsFile = args[0];
//        profilingFile = args[1];

        
        metricsFile = "C:\\profiling\\parsed\\findbugs\\findbugs_meth_input.xls";
        profilingFile = "C:\\profiling\\parsed\\findbugs\\findbugs1_cpu_output.xls";

        
        System.out.println("Metrics: " + metricsFile);
        System.out.println("Profiling: " + profilingFile);
        
        
        //get vector of metrics
        
        
    }

    
    
    
}
