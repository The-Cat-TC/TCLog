/*
 * Copyright (C) 2019 the-c
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.tc.cat.the.tclog;

import de.tc.cat.the.exception.FileTypeException;
import de.tc.cat.the.system.GZip;
import de.tc.cat.the.system.Seperator;
import de.tc.cat.the.system.Time;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author the-c
 */
public class TCLogAction {

    protected static final Tabellog T = new Tabellog();
    private final File Ordner = new File(System.getProperty("user.home") + Seperator.fileseperator() + ".TC" + Seperator.fileseperator() + "log" + System.getProperty("file.separator"));
    private final File log = new File(Ordner.getPath() + System.getProperty("file.separator") + "log-" + new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()) + ".tclog");
    private final String[] userbegin = new String[]{Time.getDate(), Time.getTime(), "TCLog", "Info", "User Custom Entry begin"};
    private final String[] userend = new String[]{Time.getDate(), Time.getTime(), "TCLog", "Info", "User Custom Entry end"};

    protected void createLog() throws IOException {
        if (!Ordner.exists()) {
            Ordner.mkdirs();
        }
        FileWriter fw = new FileWriter(log);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            log.createNewFile();

            for (int i = 0; !T.readDatbase(false).isEmpty() && i < T.readDatbase(false).size(); i++) {
                bw.write(T.readDatbase(false).get(i));
                bw.newLine();
            }
        }
    }

    protected void clearLogFilesGz() throws IOException {
        if (!Ordner.exists()) {
            Ordner.mkdirs();
        }
        for (var f : Ordner.listFiles()) {
            f.delete();
        }
        FileWriter fw = new FileWriter(log);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            log.createNewFile();

            for (int i = 0; !T.readDatbase(false).isEmpty() && i < T.readDatbase(false).size(); i++) {
                bw.write(T.readDatbase(false).get(i));
                bw.newLine();
            }
        }
        GZip.packen(log, Ordner.getPath() + System.getProperty("file.separator") + "log-" + new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()));
    }

    protected void clearLogFilesZip() throws IOException, ZipException, FileTypeException {
        if (!Ordner.exists()) {
            Ordner.mkdirs();
        }
        for (File f : Ordner.listFiles()) {
            if (f.isFile() && f.getName().endsWith(".zip")) {
                f.delete();
            } else if (f.isFile() && f.getName().endsWith(".gz")) {
                GZip.entpacken(f);
                f.delete();
            }
        }
        FileWriter fw = new FileWriter(log);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            log.createNewFile();

            for (int i = 0; !T.readDatbase(false).isEmpty() && i < T.readDatbase(false).size(); i++) {
                bw.write(T.readDatbase(false).get(i));
                bw.newLine();
            }
        }
        ZipFile zf = new ZipFile(Ordner.getPath() + System.getProperty("file.separator") + "log-" + new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()) + ".zip");
        zf.addFolder(Ordner);
        for (File f: Ordner.listFiles())
        {
            if (f.isFile() && f.getName().endsWith(".tclog"))
            {
                f.delete();
            }
        }
    }

    protected void clearLog() {
        T.deleteDate();
    }

}
