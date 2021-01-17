/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog.export;

import de.tc.cat.the.tclog.Command;
import de.tc.cat.the.tclog.TCLog;

/**
 * The Log class gives you all the options to create a log and use it in your own code.
 */
public class Log {

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param host    the host name where the entry comes from. Which server or client sent the entry.
     * @param message The log entry that is written is the message or the text.
     * @param logType The log level or the log type such as error or warning.
     */
    public static void log(String from, String host, String message, LogType logType) {
        TCLog.process(from, host, message, logType, Thread.currentThread(), ProcessHandle.current());
    }

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param host    the host name where the entry comes from. Which server or client sent the entry.
     * @param message The log entry that is written is the message or the text.
     */
    public static void log(String from, String host, String message) {
        TCLog.process(from, host, message, LogType.Info, Thread.currentThread(), ProcessHandle.current());
    }

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param message The log entry that is written is the message or the text.
     */
    public static void log(String from, String message) {
        TCLog.process(from, "localhost", message, LogType.Info, Thread.currentThread(), ProcessHandle.current());
    }

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param message The log entry that is written is the message or the text.
     * @param logType The log level or the log type such as error or warning.
     */
    public static void log(String from, String message, LogType logType) {
        TCLog.process(from, "localhost", message, logType, Thread.currentThread(), ProcessHandle.current());
    }

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param host    the host name where the entry comes from. Which server or client sent the entry.
     * @param message The log entry that is written is the message or the text.
     * @param logType The log level or the log type such as error or warning.
     * @param th      The thread from which the log was written.
     * @param pr      The process from which the log was written.
     */
    public static void log(String from, String host, String message, LogType logType, Thread th, ProcessHandle pr) {
        TCLog.process(from, host, message, logType, th, pr);
    }

    /**
     * Creates a log entry with the exception of the type Error.
     *
     * @param from Who the entry comes from speaks program or the same.
     * @param ex   The exception to the following.
     */
    public static void log(String from, Exception ex) {
        if (ex.getMessage().isEmpty() || ex.getMessage().isBlank()) {
            TCLog.process(from, "localhost", ex.toString().substring(ex.toString().lastIndexOf(".") + 1), LogType.Error, Thread.currentThread(), ProcessHandle.current());
        } else {
            TCLog.process(from, "localhost", ex.getMessage(), LogType.Error, Thread.currentThread(), ProcessHandle.current());
        }
    }

    /**
     * Experts the log as HTML output.
     */
    public static void exportHTML() {
        Command cmd = new Command(new String[]{"--export-html"});
        try {
            cmd.cmd();
        } catch (Exception e) {
            log("TCLog",e);
        }
    }

    /**
     * Experts the log as SQL output.
     */
    public static void exportSQL() {
        Command cmd = new Command(new String[]{"--export-sql"});
        try {
            cmd.cmd();
        } catch (Exception e) {
            log("TCLog",e);
        }
    }
}
