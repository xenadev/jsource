/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsource.projectsmanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author TOSHIBA
 */
public class ProjectIO {

    public static boolean saveProject(Project project, String projectPath) {
        boolean isSaved = true;

        try {

            FileOutputStream fout = new FileOutputStream(projectPath);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(project);
            oos.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            isSaved = false;
        }

        return isSaved;
    }

    public static Project openProject(String projectPath) {
        Project result = null;

        try {

            FileInputStream fin = new FileInputStream(projectPath);
            ObjectInputStream ois = new ObjectInputStream(fin);
            result = (Project) ois.readObject();
            ois.close();

        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
