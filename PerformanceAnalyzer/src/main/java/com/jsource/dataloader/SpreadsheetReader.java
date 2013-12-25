package com.jsource.dataloader;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.jsource.perfanalyzer.ClassMetric;
import com.jsource.perfanalyzer.MethodMetric;

import java.io.*;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFCell;

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

        result = readColumns(metricNames, filePath);

//        if (metricNames.length == ClassMetric.values().length) {
//            result = new String[][]{
//                {"ProfileController", "15", "23", "1", "243"},
//                {"RPMAdapter", "14", "14", "1", "153"},
//                {"RPMUtils", "1", "22", "0.29", "150"},
//                {"TestController", "8", "9", "1", "130"},
//                {"Serializer", "1", "9", "0.67", "64"},
//                {"SubscriptionController", "10", "8", "0", "63"},
//                {"SubscriptionBean", "0", "11", "0.11", "37"}};
//        } else if (metricNames.length == MethodMetric.values().length) {
//            result = new String[][]{
//                {"setAppVersion", "0", "1", "0", "3", "1", "0", "2", "0",
//                    "0", "0", "0", "Application"},
//                {"getOptionsForUpdate", "0", "1", "0", "23", "1", "0",
//                    "10", "3", "0.67", "0", "0", "ProfileController"}};
//
//        }
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

            Vector cellVectorHolder = new Vector();

            // iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            int rowCount = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // for each row iterate through each column
                Iterator<Cell> cellIterator = row.cellIterator();
                Vector cellStoreVector = new Vector();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    cellStoreVector.addElement(cell);

                }
                if (rowCount != 0) {
                    cellVectorHolder.addElement(cellStoreVector);
                } else {
                    rowCount++;
                }

            }

            result = convertCellVectorToArray(cellVectorHolder);

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

        return result;
    }

    private String[][] convertCellVectorToArray(Vector dataHolder) {
        String[][] result = null;

        if (dataHolder != null) {
            result = new String[dataHolder.size()][((Vector) dataHolder.elementAt(0)).size()];

            for (int i = 0; i < dataHolder.size(); i++) {

                Vector cellStoreVector = (Vector) dataHolder.elementAt(i);
                String[] columns = new String[cellStoreVector.size()];
                for (int j = 0; j < cellStoreVector.size(); j++) {
                    HSSFCell myCell = (HSSFCell) cellStoreVector.elementAt(j);
                    columns[j] = myCell.toString();
                }
                result[i] = columns;

            }

        }

        return result;
    }

}
