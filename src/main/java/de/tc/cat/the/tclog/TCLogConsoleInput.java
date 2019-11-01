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

import de.tc.cat.the.system.Time;
import static de.tc.cat.the.tclog.ReadConsole.*;
import static de.tc.cat.the.tclog.TCLogARGS1.args1;
import static de.tc.cat.the.tclog.TCLogAction.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author the-c
 */
public class TCLogConsoleInput {

    private static final String[] S = new String[]{};
    private static final TCLogAction TCLOGA = new TCLogAction();
    private static final Scanner SCN = new Scanner(System.in);

    public static void ExeceptionFilter() {
        try {
            inf("TCLog");
            inf("version 1.0.6");
            inf("Console version 1.0.0");
            input();
        } catch (SQLException ex) {
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "SQLException");
        } catch (InputMismatchException ex) {
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "InputMismatchException");
        } catch (IOException ex) {
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "IOException");
        }
    }
    
    public static void ExeceptionFilterDebug() {
        try {
            inf("TCLog");
            inf("version 1.0.6");
            inf("Console version 1.0.0");
            inf("Debug mode");
            input();
        } catch (SQLException ex) {
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "SQLException");
            Logger.getLogger(TCLogConsoleInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputMismatchException ex) {
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "InputMismatchException");
            Logger.getLogger(TCLogConsoleInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "IOException");
            Logger.getLogger(TCLogConsoleInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void input() throws SQLException, InputMismatchException, IOException, ZipException {
        inf("");
        inf("List all logs [0]");
        inf("List error logs [1]");
        inf("List warning logs [2]");
        inf("List info logs [3]");
        inf("List other logs [4]");
        inf("Clear Log [5]");
        inf("Clear Log Files [6]");
        inf("Create Log File [7]");
        inf("Add Log Entry [8]");
        inf("Exit [9]");
        System.out.print("Gebe eine Zahl an:");
        int i = SCN.nextInt();

        switch (i) {
            case 0:
                args1(new String[]{"-r"});
                input();
                break;
            case 1:
                args1(new String[]{"-e"});
                input();
                break;
            case 2:
                args1(new String[]{"-w"});
                input();
                break;
            case 3:
                args1(new String[]{"-i"});
                input();
                break;
            case 4:
                args1(new String[]{"-m"});
                input();
                break;
            case 5:
                TCLOGA.clearLog();
                input();
                break;
            case 6:
                ClearLogFiles();
                input();
                break;
            case 7:
                TCLOGA.createLog();
                input();
                break;
            case 8:
                AddLog();
                input();
                break;
            case 9:
                System.exit(0);
                break;
            default:
                err("Falsche zahl bitte geben sie eine Zahl von 0-9 an.");
                input();
        }

    }

    private static void AddLog() throws SQLException, InputMismatchException, IOException, ZipException {
        inf("AddError [0]");
        inf("AddWarning [1]");
        inf("AddInfo [2]");
        inf("AddCustom [3]");
        inf("Back [4]");
        System.out.print("Gebe eine Zahl an:");
        int i = SCN.nextInt();
        String user;
        String msg;
        String type;
        switch (i) {
            case 0:
                System.out.print("User oder Application: ");
                user = SCN.nextLine();
                System.out.print("Message eingeben: ");
                msg = SCN.nextLine();
                TCLOGA.addLogEntryError(user, msg);
                break;
            case 1:
                System.out.print("User oder Application: ");
                user = SCN.nextLine();
                System.out.print("Message eingeben: ");
                msg = SCN.nextLine();
                TCLOGA.addLogEntryWarn(user, msg);
                break;
            case 2:
                System.out.print("User oder Application: ");
                user = SCN.nextLine();
                System.out.print("Message eingeben: ");
                msg = SCN.nextLine();
                TCLOGA.addLogEntryInfo(user, msg);
                break;
            case 3:
                System.out.print("User oder Application: ");
                user = SCN.nextLine();
                System.out.print("Geben Sie en Log Type an: ");
                type = SCN.nextLine();
                System.out.print("Message eingeben: ");
                msg = SCN.nextLine();
                TCLOGA.addLogEntryDev(user, type, msg);
                break;
            case 4:
                System.out.flush();
                input();
                break;
            default:
                err("Falsche zahl bitte geben sie eine Zahl von 0-4 an.");
                AddLog();
        }
    }
    
    private static void ClearLogFiles() throws IOException, ZipException, SQLException
    {
        inf("Create gz File [0]");
        inf("Create zip File [1]");
        inf("Exit [2]");
        System.out.print("Gebe eine Zahl an:");
        int i = SCN.nextInt();
        switch (i)
        {
            case 0:
                TCLOGA.clearLogFilesGz();
                break;
            case 1:
                TCLOGA.clearLogFilesZip();
                break;
            case 2:
                System.out.flush();
                input();
                break;
            default:
                err("Falsche zahl bitte geben sie eine Zahl von 0-2 an.");
                ClearLogFiles();
        }
    }
}
