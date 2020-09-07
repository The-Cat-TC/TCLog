package de.tc.cat.the.tclog.export;

import de.tc.cat.the.tclog.TCLog;

import java.util.ArrayList;
import java.util.List;

import static de.tc.cat.the.tclog.StaticVariable.xml;

/**
 * The Log class gives you all the options to create a log and use it in your own code.
 */
public class Log {
    private static final List<Exception> exc = new ArrayList<>();

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param host    the host name where the entry comes from. Which server or client sent the entry.
     * @param message The log entry that is written is the message or the text.
     * @param logType The log level or the log type such as error or warning.
     */
    public static void log(String from, String host, String message, LogType logType) {
        readException();
        TCLog.main(new String[]{from, host, message, logType.name()});
    }

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param host    the host name where the entry comes from. Which server or client sent the entry.
     * @param message The log entry that is written is the message or the text.
     */
    public static void log(String from, String host, String message) {
        readException();
        TCLog.main(new String[]{from, host, message});
    }

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param message The log entry that is written is the message or the text.
     */
    public static void log(String from, String message) {
        readException();
        TCLog.main(new String[]{from, message});
    }

    /**
     * Creates a log entry.
     *
     * @param from    the program that creates the entry.
     * @param message The log entry that is written is the message or the text.
     * @param logType The log level or the log type such as error or warning.
     */
    public static void log(String from, String message, LogType logType) {
        readException();
        TCLog.main(new String[]{from, "localhost", message, logType.name()});
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
        readException();
        TCLog.process(from, host, message, logType, th, pr);
    }

    private static void readException() {
        for (Exception ex : exc) {
            xml.addException(ex);
        }
        exc.clear();
    }

    /**
     * If you add an exception to the list, exceptions can be logged.
     *
     * @param ex The exception to the following.
     */
    public static void addException(Exception ex) {
        exc.add(ex);

    }

    /**
     * Retrieve all collected exceptions.
     *
     * @return returns the collected exceptions as <code>List<Exception> </code>.
     */
    public static List<Exception> getException() {
        return exc;
    }

    /**
     * Experts the log as HTML output.
     */
    public static void exportHTML() {
        TCLog.main(new String[]{"--export-html"});
    }
}
