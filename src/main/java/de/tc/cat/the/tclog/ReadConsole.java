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

    protected static void inf(String msg) {
        cp = new ColoredPrinter.Builder(2, false)
                .background(Ansi.BColor.BLACK)
                .foreground(Ansi.FColor.WHITE)
                .build();
        cp.println(msg);
    }

    protected static void unk(String msg) {
        cp = new ColoredPrinter.Builder(2, false)
                .background(Ansi.BColor.BLACK)
                .foreground(Ansi.FColor.MAGENTA)
                .build();
        cp.println(msg);
    }

    protected static void readConsole() {

        for (int i = 0; !T.readDatbase().isEmpty() && i < T.readDatbase().size(); i++) {

            if (T.readDatbase().get(i).contains("Error")) {
                    err(T.readDatbase().get(i));
            } else if (T.readDatbase().get(i).contains("Warning"))
            {
                war(T.readDatbase().get(i));
            }
            else if (T.readDatbase().get(i).contains("Info"))
                inf(T.readDatbase().get(i));
            else
            {
                unk(T.readDatbase().get(i));
            }
        }
    }

    protected static void outInfo() {

        for (int i = 0; !T.readDatbase().isEmpty() && i < T.readDatbase().size(); i++) {

            if (T.readDatbase().get(i).contains("Info")) {
                System.out.print(T.readDatbase().get(i) + "\n");
            }
        }
    }

    protected static void outWarning() {

        for (int i = 0; !T.readDatbase().isEmpty() && i < T.readDatbase().size(); i++) {

            if (T.readDatbase().get(i).contains("Warning")) {
                System.out.print(T.readDatbase().get(i) + "\n");
            }
        }
    }

    protected static void outError() {

        for (int i = 0; !T.readDatbase().isEmpty() && i < T.readDatbase().size(); i++) {
            if (T.readDatbase().get(i).contains("Error")) {

                System.err.print(T.readDatbase().get(i) + "\n");
            }
        }
    }

    protected static void outDev() {
        for (int i = 0; !T.readDatbase().isEmpty() && i < T.readDatbase().size(); i++) {

            if (!T.readDatbase().get(i).contains("Info") || !T.readDatbase().get(i).contains("Warning") || !T.readDatbase().get(i).contains("Error")) {

                System.out.print(T.readDatbase().get(i) + "\n");
            }
        }
    }
}
