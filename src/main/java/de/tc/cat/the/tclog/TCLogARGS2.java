package de.tc.cat.the.tclog;

import de.tc.cat.the.exception.FileTypeException;
import de.tc.cat.the.system.Time;
import de.tc.cat.the.tclog.export.TCLog;
import de.tc.cat.the.tclog.extern.ReadMCLog;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static de.tc.cat.the.tclog.TCLogAction.T;

public class TCLogARGS2 {
    public static void args2(String[] args) throws FileTypeException, IOException, ParseException {
        String arg1 = args[0];
        String arg2 = args[1];

        if (arg1.equals("-mc") || arg1.equals("--minecraft") && !arg2.isEmpty()) {
            ReadMCLog.readLog(new File(arg2));
        } else {
            TCLog.main(new String[]{});
            T.log(Time.getDate(), Time.getTime(), "TCLog", "Error", "Illegal Arguments for TCLog. Error 2");
        }
    }
}
