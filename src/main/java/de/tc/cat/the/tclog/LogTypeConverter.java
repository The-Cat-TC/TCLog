package de.tc.cat.the.tclog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum LogType {
    Error, Warning, Debug, Info, Unknow
}

public class LogTypeConverter {
    private final List<String> typeList = new ArrayList<>();

    public LogTypeConverter() {
        typeList.add("Error");
        typeList.add("Fatal");
        typeList.add("Fehler");
        typeList.add("Warning");
        typeList.add("Warnung");
        typeList.add("Warn");
        typeList.add("Attention");
        typeList.add("Debug");
        typeList.add("Trace");
        typeList.add("Info");
        typeList.add("Infomation");
        typeList.add("Log");
        typeList.add("Unknow");
        typeList.add("Unbekannt");
        typeList.add("Mix");
        typeList.add("Mixed");
        Collections.sort(typeList);
    }

    public String logType(String type) {
        return convert(type).name();
    }

    private LogType convert(String type) {
        for (String s : typeList) {
            if (s.equalsIgnoreCase("Error")) {
                return LogType.Error;
            } else if (s.equalsIgnoreCase("Fatal")) {
                return LogType.Error;
            } else if (s.equalsIgnoreCase("Fehler")) {
                return LogType.Error;
            } else if (s.equalsIgnoreCase("Warning")) {
                return LogType.Warning;
            } else if (s.equalsIgnoreCase("Warnung")) {
                return LogType.Warning;
            } else if (s.equalsIgnoreCase("Warn")) {
                return LogType.Warning;
            } else if (s.equalsIgnoreCase("Attention")) {
                return LogType.Warning;
            } else if (s.equalsIgnoreCase("Debug")) {
                return LogType.Debug;
            } else if (s.equalsIgnoreCase("Trace")) {
                return LogType.Debug;
            } else if (s.equalsIgnoreCase("Info")) {
                return LogType.Info;
            } else if (s.equalsIgnoreCase("Infomation")) {
                return LogType.Info;
            } else if (s.equalsIgnoreCase("Log")) {
                return LogType.Info;
            } else if (s.equalsIgnoreCase("Unknow")) {
                return LogType.Unknow;
            } else if (s.equalsIgnoreCase("Unbekannt")) {
                return LogType.Unknow;
            } else if (s.equalsIgnoreCase("Mix")) {
                return LogType.Unknow;
            } else if (s.equalsIgnoreCase("Mixed")) {
                return LogType.Unknow;
            } else {
                return LogType.valueOf(type);
            }
        }
        return LogType.valueOf(type);
    }
}
