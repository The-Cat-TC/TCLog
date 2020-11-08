/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog.parser;

import de.tc.cat.the.system.FileRecursive;
import de.tc.cat.the.tclog.export.LogType;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;

import static de.tc.cat.the.tclog.StaticVariable.*;

public class HTML {
    private static File HTMLPath = new File(homeHTML + sep + "html" + sep);

    public static void HTML() throws Exception {
        SAXBuilder sax = new SAXBuilder();
        if (!HTMLPath.exists()) {
            HTMLPath.mkdirs();
        }
        CSS.CSS();
        System.out.println("Bitte warten. HTML wird exportiert...");
        FileRecursive fr = new FileRecursive(log);
        for (String logFile : fr.file()) {
            if (logFile.contains("html") || logFile.contains("css")) {
                continue;
            } else if (logFile.endsWith(".tclog")) {
                File logFiletoFile = new File(logFile);
                createXML(sax.build(logFiletoFile), logFiletoFile);
            }
        }
        System.out.println("Log wurde nach HTML Exportiert.");
    }

    private static void createXML(Document doc, File file) throws FileNotFoundException {
        File logHtmlFile = new File(HTMLPath + sep + file.getName().substring(0, file.getName().indexOf(".")) + ".html");
        PrintStream psLogFile = new PrintStream(logHtmlFile);

        for (Element logElement : doc.getRootElement().getChildren()) {
            if (logElement.getName().equals("log")) {
                psLogFile.println("<html>");
                psLogFile.println("\t<head>");
                psLogFile.println("\t \t<title> " + logHtmlFile.getName().substring(0, logHtmlFile.getName().lastIndexOf(".")) + " </title>");
                psLogFile.println("\t \t<link type=\"text/css\" rel=\"stylesheet\" href=\"./css/style.css\" />");
                psLogFile.println("\t</head>");
                psLogFile.println("<body>");
                psLogFile.println("<div class= \"" + logType(logElement).name() + "\">");
                for (Attribute logAttribute : logElement.getAttributes()) {
                    psLogFile.println("<p>" + logAttribute.getName() + ": " + logAttribute.getValue() + "</p>");
                }
                psLogFile.println("</div>");
                psLogFile.println("<hr />");
            }
        }
        psLogFile.println("</body>");
        footer(psLogFile);
        psLogFile.println("</html>");
        psLogFile.flush();
        psLogFile.close();
    }

    private static void footer(PrintStream ps) {
        ps.println("<footer>");
        ps.println("\t<p>Powered by TClog</p>");
        LocalDate ld = LocalDate.now();
        ps.println("\t<p>The Cat &copy; 2018-" + ld.getYear() + "</p>");
        ps.println("\t<a href=\"https://www.the-cat-tc.de\">Homepage</a>");
        ps.println("<br />");
        ps.println("\t<a href=\"https://aufgaben.the-cat-tc.de\">Support</a>");
        ps.println("<br />");
        ps.println("\t<a href=\"https://wiki.the-cat-tc.de\">The Cat Wiki</a>");
        ps.println("</footer>");
        ps.flush();
        ps.close();
    }

    private static LogType logType(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            if (!att.getName().equals("LogType")) {
                continue;
            }
            if (att.getValue().equals(LogType.Info.name())) {
                return LogType.Info;
            } else if (att.getValue().equals(LogType.Warning.name())) {
                return LogType.Warning;
            } else if (att.getValue().equals(LogType.Debug.name())) {
                return LogType.Debug;
            } else if (att.getValue().equals(LogType.Error.name())) {
                return LogType.Error;
            } else {
                return LogType.Unknow;
            }
        }
        return LogType.Unknow;
    }
}
