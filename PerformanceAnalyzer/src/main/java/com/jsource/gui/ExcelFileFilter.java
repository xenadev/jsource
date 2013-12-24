/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsource.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author k.shaposhnik
 */
public class ExcelFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        // Allow only directories, or files with ".xls(x)" extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(".xls") || file.getAbsolutePath().endsWith(".xlsx");
    }

    @Override
    public String getDescription() {
        // This description will be displayed in the dialog,
        return "Excel documents (*.xls, *.xlsx)";
    }
}
