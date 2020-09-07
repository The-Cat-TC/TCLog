package de.tc.cat.the.tclog.export;

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExceptionFilter.exfilter(args);
    }
    
}
