/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsource.graphrepbuilder;

import com.jsource.perfanalyzer.MethodMetric;
import com.jsource.perfanalyzer.MetricUtils;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author TOSHIBA
 */
public class LineChartBuilder {

    public void buildChartForMethods(String[][] methodMetrics) {

        if (methodMetrics != null) {
            //convert metrics to doubles
            double[][] methodMetricsNum = MetricUtils.convertMetricsFromStrToNum(methodMetrics);

            //get values of each metric
            String[] methodName = MetricUtils.getStringColumn(methodMetrics, MethodMetric.METHOD_NAME.getOrderNum());
            String[] scopeName = MetricUtils.getStringColumn(methodMetrics, MethodMetric.SCOPE_NAME.getOrderNum());
            double[] atfd = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.ATFD.getOrderNum());
            double[] laa = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.LAA.getOrderNum());
            double[] fdp = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.FDP.getOrderNum());
            double[] loc = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.LOC.getOrderNum());
            double[] cyclo = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CYCLO.getOrderNum());
            double[] maxnesting = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.MAXNESTING.getOrderNum());
            double[] noav = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.NOAV.getOrderNum());
            double[] cint = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CINT.getOrderNum());
            double[] cdisp = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CDISP.getOrderNum());
            double[] cm = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CM.getOrderNum());
            double[] cc = MetricUtils.getDoubleColumn(methodMetricsNum, MethodMetric.CC.getOrderNum());

            //create dataset
            final XYSeries atfdSeries = new XYSeries("ATFD");
            for (int i = 0; i < maxnesting.length; i++) {
                atfdSeries.add(i, maxnesting[i]);
            }

            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(atfdSeries);

//            JFreeChart chart = 
            //createChart(dataset);
//            JFreeChart chart = ChartFactory.createXYLineChart("Method metrics", "Methods", "ATFD", dataset, PlotOrientation.VERTICAL, true, true, false);
//            ChartFrame frame1 = new ChartFrame("Method metrics", chart);
//            frame1.setVisible(true);
//            frame1.setSize(600, 600);
            drawHistogram(atfd, maxnesting);

        }
    }

    public void buildChartForClasses(String[][] classMetrics) {

    }

    /**
     * Creates a chart.
     *
     * @param dataset the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Demo 6", // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

    public void drawHistogram(double[] atfd, double[] maxnesting) {
        JFreeChart chart = null;
        for (int i = 0; i < atfd.length; i++) {
            HistogramDataset dataset = new HistogramDataset();
            dataset.setType(HistogramType.FREQUENCY);
            dataset.addSeries("CDISP", atfd, 50);
            dataset.addSeries("MAXNESTING", maxnesting, 50);
            String plotTitle = "Metrics distributions";
            String xaxis = "Metric value";
            String yaxis = "";
            PlotOrientation orientation = PlotOrientation.VERTICAL;
            boolean show = false;
            boolean toolTips = false;
            boolean urls = false;
            chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis,
                    dataset, orientation, show, toolTips, urls);
        }
        ChartFrame frame1 = new ChartFrame("Method-level metrics", chart);
        frame1.setVisible(true);
        frame1.setSize(600, 600);

    }

}
