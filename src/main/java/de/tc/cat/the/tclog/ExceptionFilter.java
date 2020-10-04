package de.tc.cat.the.tclog;

import de.tc.cat.the.exception.FileTypeException;
import de.tc.cat.the.tclog.export.TCLog;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ExceptionFilter {
    public static void exfilter(String[] args) {
        try {

            Firstrun.create();
            Tabellog table = new Tabellog();
            switch (args.length) {
                case 3:
                    table.log(args);
                    break;
                case 5:
                    table.log(args);
                    break;
                case 1:
                    TCLogARGS1.args1(args);
                    break;
                case 2:
                    TCLogARGS2.args2(args);
                    break;
                default:
                    TCLog.main(new String[]{"TCLog", "Error", "Illegal Arguments for TCLog."});
                    break;

            }
        } catch (IOException e) {
            TCLog.main(new String[]{"TCLog", "Error", "IO Exception"});
            TCLog.main(new String[]{"TCLog", "Debug", e.getLocalizedMessage()});
        } catch (SQLException e) {
            TCLog.main(new String[]{"TCLog", "Error", "SQL Exception"});
            TCLog.main(new String[]{"TCLog", "Debug", e.getLocalizedMessage()});
        } catch (FileTypeException e) {
            TCLog.main(new String[]{"TCLog", "Error", "FileTypeException Exception"});
            TCLog.main(new String[]{"TCLog", "Debug", e.getLocalizedMessage()});
        } catch (JDOMException e) {
            TCLog.main(new String[]{"TCLog", "Error", "JDOM Exception"});
            TCLog.main(new String[]{"TCLog", "Debug", e.getLocalizedMessage()});
        } catch (ParseException e) {
            TCLog.main(new String[]{"TCLog", "Error", "Parse Exception"});
            TCLog.main(new String[]{"TCLog", "Debug", e.getLocalizedMessage()});
        }
    }
}