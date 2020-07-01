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

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import de.tc.cat.the.system.Time;
import de.tc.cat.the.tclog.export.TCLog;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author the-c
 */
public class ReadConsole {

    private static ColoredPrinter cp;
    private static final Tabellog T = new Tabellog();

    protected static void err(String msg) {
        cp = new ColoredPrinter.Builder(3, false)
                .background(Ansi.BColor.BLACK)
                .foreground(Ansi.FColor.RED)
                .build();

        cp.println(msg);
    }

    protected static void war(String msg) {
        cp = new ColoredPrinter.Builder(2, false)
                .background(Ansi.BColor.BLACK)
                .foreground(Ansi.FColor.YELLOW)
                .build();
        cp.println(msg);
    }

    protected static void deb(String msg) {
        cp = new ColoredPrinter.Builder(4, false)
                .background(Ansi.BColor.BLACK)
                .foreground(Ansi.FColor.CYAN)
                .build();
        cp.print(msg);
    }

    protected static void inf(String msg) {
        cp = new ColoredPrinter.Builder(1, false)
                .background(Ansi.BColor.BLACK)
                .foreground(Ansi.FColor.WHITE)
                .build();
        cp.println(msg);
    }

    protected static void unk(String msg) {
        cp = new ColoredPrinter.Builder(5, false)
                .background(Ansi.BColor.BLACK)
                .foreground(Ansi.FColor.MAGENTA)
                .build();
        cp.println(msg);
    }

    protected static void readConsole() {

        for (int i = 0; !T.readDatbase(true).isEmpty() && i < T.readDatbase(true).size(); i++) {

            switch (readType(T.readDatbase(true).get(i))) {
                case "Error":
                    err(T.readDatbase(true).get(i));
                    break;
                case "Warning":
                    war(T.readDatbase(true).get(i));
                    break;
                case "Info":
                    inf(T.readDatbase(true).get(i));
                    break;
                case "Debug":
                    deb(T.readDatbase(true).get(i));
                    System.out.println();
                    break;
                default:
                    unk(T.readDatbase(true).get(i));
                    break;
            }
        }
    }

    protected static void outInfo() {

        for (int i = 0; !T.readDatbase(true).isEmpty() && i < T.readDatbase(true).size(); i++) {

            if (readType(T.readDatbase(true).get(i)).equals("Info")) {
                inf(T.readDatbase(true).get(i) + "\n");
            }
        }
    }

    protected static void outWarning() {

        for (int i = 0; !T.readDatbase(true).isEmpty() && i < T.readDatbase(true).size(); i++) {

            if (readType(T.readDatbase(true).get(i)).equals("Warning")) {
                war(T.readDatbase(true).get(i) + "\n");
            }
        }
    }

    protected static void outError() {

        for (int i = 0; !T.readDatbase(true).isEmpty() && i < T.readDatbase(true).size(); i++) {
            if (readType(T.readDatbase(true).get(i)).equals("Error")) {

                err(T.readDatbase(true).get(i) + "\n");
            }
        }
    }

    protected static void outDebug() {
        for (int i = 0; !T.readDatbase(true).isEmpty() && i < T.readDatbase(true).size(); i++) {
            if (readType(T.readDatbase(true).get(i)).equals("Debug")) {

                deb(T.readDatbase(true).get(i) + "\n");
            }
        }
    }

    protected static void outDev() {
        for (int i = 0; !T.readDatbase(true).isEmpty() && i < T.readDatbase(true).size(); i++) {
            switch (readType(T.readDatbase(true).get(i))) {
                case "Info":
                    break;
                case "Error":
                    break;
                case "Warning":
                    break;
                case "Debug":
                    break;
                default:
                    unk(T.readDatbase(true).get(i) + "\n");
                    break;
            }
        }
    }

    private static String readType(String type)
    {
        String[] split = type.split("\t");
        return split[4];
    }
    public static void log(String from,String type,String msg) throws IOException, SQLException {
        TCLog.main(new String[] {Time.getDate(),Time.getTime(),from,type,msg});
    }
}