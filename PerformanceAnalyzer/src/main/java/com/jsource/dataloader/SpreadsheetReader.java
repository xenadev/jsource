package com.jsource.dataloader;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

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
        String[][] result = readColumns(metricNames, filePath);
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

    /**
     * Converts two-dim cell vector to string two-dim array
     *
     * @param dataHolder Cells
     * @return String cell values
     */
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
