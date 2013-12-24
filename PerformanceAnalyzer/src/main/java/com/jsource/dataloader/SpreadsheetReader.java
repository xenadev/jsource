package com.jsource.dataloader;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.jsource.perfanalyzer.ClassMetric;
import com.jsource.perfanalyzer.MethodMetric;

import java.io.*;
import java.util.Iterator;

/**
 * @author Kseniya Shaposhnikova
 * @file SpreadsheetReader.java
 * @brief
 * @date Dec 23, 2013 11:35:08 AM
 */
public class SpreadsheetReader implements MetricsReader {

    /*
     * (non-Javadoc)
     * 
     * @see com.jsource.dataloader.MetricsReader#readMetrics(java.lang.String[],
     * java.lang.String)
     */
    public String[][] readMetrics(String[] metricNames, String filePath) {
	String[][] result = null;

	if (metricNames.length == ClassMetric.values().length) {
	    result = new String[][] {
		    { "ProfileController", "15", "23", "1", "243" },
		    { "RPMAdapter", "14", "14", "1", "153" },
		    { "RPMUtils", "1", "22", "0.29", "150" },
		    { "TestController", "8", "9", "1", "130" },
		    { "Serializer", "1", "9", "0.67", "64" },
		    { "SubscriptionController", "10", "8", "0", "63" },
		    { "SubscriptionBean", "0", "11", "0.11", "37" } };
	}

	else if (metricNames.length == MethodMetric.values().length) {
	    result = new String[][] {
		    { "setAppVersion", "0", "1", "0", "3", "1", "0", "2", "0",
			    "0", "0", "0", "Application" },
		    { "getOptionsForUpdate", "0", "1", "0", "23", "1", "0",
			    "10", "3", "0.67", "0", "0", "ProfileController" } };

	}

	return result;
    }

    /**
     * Reads columns from specified spreadsheet.
     * 
     * @param columnNames
     * @param filePath
     */
    private String[][] readColumns(String[] columnNames, String filePath) {
	String[][] result = null;

	try {
	    FileInputStream file = new FileInputStream(new File(filePath));

	    // get workbook instance for XLS sheet
	    HSSFWorkbook workbook = new HSSFWorkbook(file);

	    // get first sheet from workbook
	    HSSFSheet sheet = workbook.getSheetAt(0);

	    // iterate through each rows from first sheet
	    Iterator<Row> rowIterator = sheet.iterator();

	    while (rowIterator.hasNext()) {
		Row row = rowIterator.next();

		// for each row iterate through each column
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {

		    Cell cell = cellIterator.next();

		    switch (cell.getCellType()) {

		    case Cell.CELL_TYPE_BOOLEAN:
			System.out.print(cell.getBooleanCellValue() + "\t\t");
			break;
		    case Cell.CELL_TYPE_NUMERIC:
			System.out.print(cell.getNumericCellValue() + "\t\t");
			break;
		    case Cell.CELL_TYPE_STRING:
			System.out.print(cell.getStringCellValue() + "\t\t");
			break;

		    }

		}

	    }

	    file.close();
	    FileOutputStream out = new FileOutputStream(new File(filePath));
	    workbook.write(out);
	    out.close();

	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

}
