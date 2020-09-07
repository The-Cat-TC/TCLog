package de.tc.cat.the.tclog.parser;

import de.tc.cat.the.system.FileRecursive;
import de.tc.cat.the.tclog.export.LogType;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

import static de.tc.cat.the.tclog.StaticVariable.*;

public class HTML {

    public static void HTML() throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        if (!new File(homeHTML + sep + "html" + sep).exists()) {
            new File(homeHTML + sep + "html" + sep).mkdirs();
        }
        CSS.CSS();
        File index = new File(homeHTML + sep + "html" + sep + "index.html");
        index.createNewFile();
        PrintStream psIndex = new PrintStream(index);
        psIndex.println("<!DOCTYPE html>");
        psIndex.println("<html>");
        psIndex.println("\t<head>");
        psIndex.println("\t \t<title>tclog-index</title>");
        psIndex.println("\t \t<link type=\"text/css\" rel=\"stylesheet\" href=\"./css/style.css\" />");
        psIndex.println("\t</head>");
        psIndex.println("\t<body>");
        psIndex.println("\t \t<h2>List of logs</h2>");
        FileRecursive fr = new FileRecursive(log);
        for (String dir : fr.dir()) {
            File logdir = new File(dir);
            File f2 = new File(log + sep + "html" + sep + logdir.getName());
            if (f2.getName().equals("html") || f2.getName().equals("css")) {
                continue;
            }
            if (!f2.exists()) {
                f2.mkdirs();
            }
            for (String s : fr.file()) {
                File f = new File(s);
                if (f.getName().endsWith(".html") || f2.getName().equals("html") || f.getName().endsWith(".css") || f2.getName().equals("css")) {
                    continue;
                }
                Document doc = sax.build(f);
                if (!f.exists()) {
                    throw new FileNotFoundException(f.getAbsolutePath() + "is not exist.");
                }
                if (f.getName().endsWith(".tclog")) {
                    File logFile = new File(homeHTML + sep + "html" + sep + f2.getName() + sep + f.getName().replaceAll(".tclog", ".html"));
                    psIndex.println("\t \t<a href=./" + f2.getName() + "/" + logFile.getName() + ">" + logFile.getName().substring(0, logFile.getName().indexOf(".")) + " " + f2.getName() + "</a>");
                    psIndex.println("<br />");
                    logFile.createNewFile();
                    PrintStream psLog = new PrintStream(logFile);
                    psLog.println("<!DOCTYPE html>");
                    psLog.println("<html>");
                    psLog.println("\t<head>");
                    psLog.println("\t \t<title>" + f2.getName() + "</title>");
                    psLog.println("\t \t<link type=\"text/css\" rel=\"stylesheet\" href=\"../css/style.css\" />");
                    psLog.println("\t</head>");
                    psLog.println("\t<body>");
                    for (Element elm : doc.getRootElement().getChildren()) {
                        if (elm.getName().equals("log")) {
                            psLog.println("\t \t<div class=\"log\">");
                            for (Attribute att : elm.getAttributes()) {
                                psLog.println("\t \t \t<p class=\"" + logType(elm) + "\">" + att.getName() + ": " + att.getValue() + "</p>");
                            }
                            if (!elm.getChildren().isEmpty()) {
                                for (Element Exceptions : elm.getChildren()) {
                                    if (!Exceptions.getName().equals("exceptions")) {
                                        continue;
                                    }
                                    psLog.println("<div class=\"exception\">");
                                    for (Element ex : Exceptions.getChildren()) {
                                        psLog.println("<a href=\"./" + ex.getName() + ".html\">" + ex.getName() + "</a>");
                                        psLog.println("<br />");
                                    }
                                    psLog.println("</div>");
                                }
                                psLog.println("\t \t</div>");
                                psLog.println("<hr />");
                            } else {
                                psLog.println("<hr />");
                            }
                        }
                    }
                    psLog.println("\t</body>");
                    footer(psLog);
                    psLog.println("</html>");
                    psLog.flush();
                    psLog.close();

                } else if (f.getName().contains(".exception")) {
                    File ex = new File(homeHTML + sep + "html" + sep + f2.getName() + sep + f.getName() + ".html");
                    ex.createNewFile();
                    PrintStream psException = new PrintStream(ex);
                    psException.println("<!DOCTYPE html>");
                    psException.println("<html>");
                    psException.println("\t<head>");
                    psException.println("\t \t<title>" + f.getName() + "</title>");
                    psException.println("<\t \t<link type=\"text/css\" rel=\"stylesheet\" href=\"../css/style.css\" />");
                    psException.println("\t</head>");
                    psException.println("\t<body>");
                    for (Element root : doc.getRootElement().getChildren()) {
                        psException.println("\t \t<h2>Exceptions</h2>");
                        psException.println("\t \t<h3>" + root.getName() + "</h3>");
                        psException.println("\t \t<div class=\"" + root.getName() + "\">");
                        for (Element exc : root.getChildren()) {
                            if (exc.getName().equals("finished-reading-of-exception-in-class") || exc.getName().equals("finished-reading-of-exception-in-class:")
                                    || exc.getText().equals("finished-reading-of-exception-in-class") || exc.getText().equals("finished-reading-of-exception-in-class:")) {
                                psException.println("<hr />");
                                continue;
                            }
                            psException.println("\t \t \t<p>" + exc.getName() + ": " + exc.getText() + "</p>");
                        }
                        psException.println("\t \t</div>");
                    }
                    psException.println("\t</body>");
                    footer(psException);
                    psException.println("</html>");
                    psException.flush();
                    psException.close();
                }
            }
        }
        psIndex.println("<hr />");
        psIndex.println("\t</body>");
        footer(psIndex);
        psIndex.println("</html>");
        psIndex.flush();
        psIndex.close();
    }

    private static void footer(PrintStream ps) {
        ps.println("<footer>");
        ps.println("\t<p>Powered by TClog");
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
