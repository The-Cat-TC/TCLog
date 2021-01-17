/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog.export;

/**
 * Checks the log type or the content of a text if it contains a valid log type.
 */
public class ChckLogType {
    /**
     * Checks the log type using a string.
     * @param logtype Returns the string containing the log type.
     * @return Returns the log type that was found or Unknow if none was found.
     */
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

    /**
     * Checks the log type using a string.
     * @param logtype Returns the LogType containing the log type.
     * @return Returns the log type that was found or Unknow if none was found.
     */
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
