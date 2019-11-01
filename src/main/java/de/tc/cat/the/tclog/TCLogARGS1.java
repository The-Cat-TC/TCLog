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
import static de.tc.cat.the.tclog.TCLogAction.T;
import static de.tc.cat.the.tclog.TCLogConsoleInput.ExeceptionFilterDebug;
import static de.tc.cat.the.util.ConsoleColorOut.*;
import java.io.IOException;

/**
 *
 * @author the-c
 */
public class TCLogARGS1 {

    public static void args1(String[] args) throws IOException {
        String arg1 = args[0].replaceAll("[_[^\\w\\dƒ÷‹‰ˆ¸ñ\\+\\- ]]", "");

        if ("-d".contains(arg1) || "--debug".contains(arg1)) {
            ExeceptionFilterDebug();
        }
        else if ("-v".contains(arg1) || "--version".contains(arg1))
        {
            printlnInfo("TCLog version 1.0.6");
        }
        else if ("-cl".equals(arg1) || "--clear-log".equals(arg1))
        {
            new TCLogAction().clearLog();
        }
        else if ("-cz".equals(arg1) || "--clear-zip".equals(arg1))
        {
            new TCLogAction().clearLogFilesZip();
        }
        else if ("-cg".equals(arg1) || "--clear-gzip".equals(arg1))
        {
            new TCLogAction().clearLogFilesGz();
        }
        else if ("-clf".equals(arg1) || "--create-logfile".equals(arg1))
        {
            new TCLogAction().createLog();
        }
         else if (arg1.equals("-i") || arg1.equals("--info"))
        {
            outInfo();
        }
        else if(arg1.equals("-w") || arg1.equals("--warning"))
        {
            outWarning();
        }
        else if (arg1.equals("-e") || arg1.equals("--error"))
        {
            outError();
        }
        else if(arg1.equals("-m") || arg1.equals("--mix"))
        {
            outDev();
        }
        else if (arg1.equals("-r") || arg1.equals("--read"))
        {
            readConsole();
        }
        else
        {
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "Illegal Arguments for TCLog. Error 2");
        }
    }
}
