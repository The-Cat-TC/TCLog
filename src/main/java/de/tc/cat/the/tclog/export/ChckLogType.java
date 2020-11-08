/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog.export;

public class ChckLogType {
    public static LogType check(String logtype) {
        if (logtype.equals(LogType.Warning.name())) {
            return LogType.Warning;
        } else if (logtype.equals(LogType.Error.name())) {
            return LogType.Error;
        } else if (logtype.equals(LogType.Info.name())) {
            return LogType.Info;
        } else if (logtype.equals(LogType.Debug.name())) {
            return LogType.Debug;
        } else {
            return LogType.Unknow;
        }
    }

    public static LogType check(LogType logtype) {
        if (logtype == LogType.Warning) {
            return LogType.Warning;
        } else if (logtype == LogType.Error) {
            return LogType.Error;
        } else if (logtype == LogType.Info) {
            return LogType.Info;
        } else if (logtype == LogType.Debug) {
            return LogType.Debug;
        } else {
            return LogType.Unknow;
        }
    }
}
