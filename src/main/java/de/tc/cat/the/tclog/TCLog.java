/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog;

import static de.tc.cat.the.tclog.StaticVariable.confDir;
import static de.tc.cat.the.tclog.StaticVariable.homeHTML;
import static de.tc.cat.the.tclog.StaticVariable.sep;
import static de.tc.cat.the.tclog.StaticVariable.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.tc.cat.the.tclog.export.Log;
import de.tc.cat.the.tclog.export.LogType;
import de.tc.cat.the.tclog.parser.XML;
import de.tc.cat.the.util.ConsoleColorOut;
@SuppressWarnings({"resource"})
public class TCLog {


    public static void process(String from, String host, String msg, LogType logType, Thread th, ProcessHandle pr) {
        try {
            if (!confDir.exists()) {
                confDir.mkdirs();
            }
            Config.firstConfig();
            xml = new XML(th, pr);
            xml.log(from, host, msg, logType);
        } catch (Exception ex) {
            Log.log("TCLog", ex);
        }

    }

    public static void main(String[] args) {
        try {
            if (!confDir.exists()) {
                confDir.mkdirs();
            }
            Config.firstConfig();
            action();

            read();

        } catch (Exception ex) {
            Log.log("TCLog", ex);
        }
    }

    private static void read() {
        try {
            List<File> files = new ArrayList<File>();
            Scanner scn = new Scanner(System.in);
            File path = new File(homeHTML + sep + "log" + sep);


            for (File file : path.listFiles()) {
                if (file.isFile()) {
                    files.add(file);
                } else {
                    continue;
                }
            }
            if (files.size() == 0) {
                System.out.println("No Log Files found. Press 0 to exit.");
            } else {
                System.out.println("Select a file from 0 to " + String.valueOf(files.size() - 1) + " and " + files.size() + " from exit.");
            }
            int i = 0;
            for (File file : files) {
                System.out.println(i + ": " + file.getName());
                i++;
            }
            System.out.println(files.size() + ": Exit");
            int val = scn.nextInt();
            if (val > files.size()) {
                System.err.println("To big number please a number from 0 to " + files.size() + ".");
                throw new IndexOutOfBoundsException(val + " is out of Index for " + files.size() + ".");
            } else if (val < 0) {
                System.err.println("To big number please a number rom 0 to " + files.size() + ".");
                throw new IllegalArgumentException(val + " is not a valide number. Give a number from 0 to " + files.size() + ".");
            }
            if (val == files.size()) {
                System.exit(0);
            }
            editFile(path.listFiles()[val]);

        } catch (Exception ex) {
            Log.log("TCLog", ex);
        } finally {
            read();
        }
    }

	private static void editFile(File file) throws Exception {
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("Select Action from Log.");
            System.out.println("0 Read Log");
            System.out.println("1 Delete Log");
            System.out.println("2 return");

            switch (scn.nextInt()) {
                case 0:
                    XMLReader.read(file);
                    break;
                case 1:
                    file.deleteOnExit();
                    break;
                case 2:
                    read();
                    break;
                default:
                    continue;
            }
        }
    }

    private static void  action() {
        boolean loop = true;
        while (loop) {
            ConsoleColorOut.printlnInfo("Wählen sie eine Action:");
            ConsoleColorOut.printlnInfo("(0) Exit");
            ConsoleColorOut.printlnInfo("(1) HTML Export");
            ConsoleColorOut.printlnInfo("(2) SQL Export");
            ConsoleColorOut.printlnInfo("(3) HTML und SQL Export");
            Scanner scr = new Scanner(System.in);

            int i = scr.nextInt();

            switch (i) {
                case 0: loop = false; break;
                case 1: Log.exportHTML(); break;
                case 2: Log.exportSQL(); break;
                case 3: Log.exportHTML(); Log.exportSQL(); break;
                default:
                    ConsoleColorOut.printlnError("Die Eingabe war nicht korrekt.");
                    break;
            }
        }
    }
}
