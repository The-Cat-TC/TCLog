package de.tc.cat.the.tclog;

import de.tc.cat.the.tclog.export.Log;
import de.tc.cat.the.tclog.export.LogType;
import de.tc.cat.the.tclog.parser.XML;

import static de.tc.cat.the.tclog.StaticVariable.confDir;
import static de.tc.cat.the.tclog.StaticVariable.xml;

/**
 * This is the log class from here all logs can be entered that are supported by the TCLog.
 *
 * @author Christian Trostmann
 * @version 1.9
 * @since 8
 */
public class TCLog {


    /**
     * The main method takes over the log entry here all log commands are entered as a string array.
     * <ol>
     *     <li>First parameter date (optional)</li>
     *     <li>Second parameter time (optional)</li>
     *     <li>Third parameter by whom (required)</li>
     *     <li>Fourth parameter log type (required)</li>
     *     <li>Fifth parameter message (required)</li>
     * </ol>
     *
     * @param from
     * @param logType
     * @param host
     * @param msg
     * @param pr
     * @param th
     */
    public static void process(String from, String host, String msg, LogType logType, Thread th, ProcessHandle pr) {
        try {
            if (!confDir.exists()) {
                confDir.mkdirs();
            }
            Config.firstConfig();
            xml = new XML(th, pr);
            xml.log(from, host, msg, logType);
        } catch (Exception ex) {
            Log.addException(ex);
        } finally {
            if (!Log.getException().isEmpty()) {
                for (Exception ex : Log.getException()) {
                    xml.addException(ex);
                }
                xml.log("TClog", "Throwing Exceptions.", LogType.Error);
            }
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        Command com = new Command();
        String from;
        String host;
        String msg;
        try {
            if (!confDir.exists()) {
                confDir.mkdirs();
            }
            Config.firstConfig();
            if (!com.isCommand(args)) {
                switch (args.length) {
                    case 1:
                        break;
                    case 2:
                        from = args[0];
                        msg = args[1];
                        xml.log(from, "localhost", msg, LogType.Info);
                        break;
                    case 3:
                        from = args[0];
                        host = args[1];
                        msg = args[2];
                        xml.log(from, host, msg, LogType.Info);
                        break;
                    case 4:
                        from = args[0];
                        host = args[1];
                        msg = args[2];
                        xml.log(from, host, msg, LogType.valueOf(args[3]));
                        break;
                    default:
                        throw new RuntimeException("No Arguments to run this application.");
                }
            }

        } catch (Exception e) {
            xml.addException(e);
            e.printStackTrace(System.out);
        } finally {
            if (!Log.getException().isEmpty()) {
                for (Exception ex : Log.getException()) {
                    xml.addException(ex);
                }
                xml.log("TClog", "Throwing Exceptions.", LogType.Error);
            }
        }
    }
}
