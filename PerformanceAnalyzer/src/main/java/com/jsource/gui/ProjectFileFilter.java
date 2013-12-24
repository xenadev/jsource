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
public class ProjectFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        // Allow only directories, or files with ".pa" extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(".pa");
    }

    @Override
    public String getDescription() {
        // This description will be displayed in the dialog,
        return "Project documents (*.pa)";
    }
}
