package de.tc.cat.the.tclog;

import de.tc.cat.the.tclog.parser.HTML;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;

public class Command {
    private String arg1;
    private String arg2;

    public boolean isCommand(String[] args) throws IOException, JDOMException {
        if (args.length == 0 || args == null) {
            return false;
        }
        return checkCommand(args);
    }

    private boolean checkCommand(String[] args) throws IOException, JDOMException {
        switch (args.length) {
            case 1:
                arg1 = args[0].replaceAll("[_[^\\w\\däöüÄÖÜ.:+  \\- ]]", "");
                break;
            case 2:
                arg1 = args[0].replaceAll("[_[^\\w\\däöüÄÖÜ.:+  \\- ]]", "");
                arg2 = args[1].replaceAll("[_[^\\w\\däöüÄÖÜ.:+  \\- ]]", "");
                break;
            default:
                return false;
        }
        if (arg1.equals("--read") || arg1.equals("-r")) {
            XMLReader xml = new XMLReader();
            xml.getInfo();
            xml.getWarning();
            xml.getError();
            xml.getDebug();
            xml.getUnknow();
            return true;
        } else if (arg1.equals("--info") || arg1.equals("-i")) {
            new XMLReader().getInfo();
            return true;
        } else if (arg1.equals("--error") || arg1.equals("-e")) {
            new XMLReader().getError();
            return true;
        } else if (arg1.equals("--warning") || arg1.equals("-w")) {
            new XMLReader().getWarning();
            return true;
        } else if (arg1.equals("--debug") || arg1.equals("-d")) {
            new XMLReader().getDebug();
            return true;
        } else if (arg1.equals("--unknow") || arg1.equals("-u")) {
            new XMLReader().getUnknow();
            return true;
        } else if (arg1.equals("--export-html")) {
            HTML.HTML();
        }


        if (arg1.equals("--read") && !arg2.isEmpty() || arg1.equals("-r") && !arg2.isEmpty()) {
            XMLReader xml = new XMLReader(new File(arg2));
            xml.getInfo();
            xml.getWarning();
            xml.getError();
            xml.getDebug();
            xml.getUnknow();
            return true;
        } else if (arg1.equals("--info") && !arg2.isEmpty() || arg1.equals("-i") && !arg2.isEmpty()) {
            new XMLReader(new File(arg2)).getInfo();
            return true;
        } else if (arg1.equals("--error") && !arg2.isEmpty() || arg1.equals("-e") && !arg2.isEmpty()) {
            new XMLReader(new File(arg2)).getError();
            return true;
        } else if (arg1.equals("--warning") && !arg2.isEmpty() || arg1.equals("-w") && !arg2.isEmpty()) {
            new XMLReader(new File(arg2)).getWarning();
            return true;
        } else if (arg1.equals("--debug") && !arg2.isEmpty() || arg1.equals("-d") && !arg2.isEmpty()) {
            new XMLReader(new File(arg2)).getDebug();
            return true;
        } else if (arg1.equals("--unknow") && !arg2.isEmpty() || arg1.equals("-u") && !arg2.isEmpty()) {
            new XMLReader(new File(arg2)).getUnknow();
            return true;
        }

        return false;
    }
}
