/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog.parser;


import de.tc.cat.the.system.Time;
import de.tc.cat.the.tclog.export.LogType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;

import static de.tc.cat.the.tclog.StaticVariable.sep;

public class XML {

    private final String user = System.getProperty("user.name");
    private final String homeLOG = System.getProperty("user.home") + sep + ".TC" + sep + "log";
    private final String date = Time.getDate();
    private final String logname = date + ".tclog";
    private final File Exdir = new File(homeLOG + sep + "log");
    private final File log = new File(homeLOG + sep + "log" + sep + logname);
    private ProcessHandle pr;
    private Thread th;
    private String from;
    private String ip;
    private String host;
    private LogType logtype;
    private String msg;
    private Document doc;

    public XML(Thread th, ProcessHandle pr) {
        this.pr = pr;
        this.th = th;
        initClass();
    }

    public XML() {
        initClass();
    }

    private void initClass() {
        doc = new Document();
        Element root = new Element("tclog");
        doc.setRootElement(root);

        if (!Exdir.exists()) {
            Exdir.mkdirs();
        }

        if (th == null) {
            th = Thread.currentThread();
        }

        if (pr == null) {
            pr = ProcessHandle.current();
        }
    }

    private void writeLog() throws JDOMException, IOException {
        Element e1 = new Element("log");
        e1.setAttribute("Date", Time.getDate());
        e1.setAttribute("Time", Time.getTime());
        e1.setAttribute("User", user);
        e1.setAttribute("From", from);
        e1.setAttribute("IP", ip);
        e1.setAttribute("Host", host);
        e1.setAttribute("LogType", logtype.name());
        e1.setAttribute("Thread", th.getName());
        e1.setAttribute("PID", String.valueOf(pr.pid()));
        e1.setAttribute("Message", msg);
        if (log.exists()) {
            SAXBuilder build = new SAXBuilder();
            doc = build.build(log);
            doc.getRootElement().addContent(e1);
            writeXML(doc, log);
        } else {
            doc.getRootElement().addContent(e1);
            writeXML(doc, log);
        }


    }

    private void writeXML(Document doc, File f) {
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        try (FileOutputStream fos = new FileOutputStream(f)) {
            XMLOutputter op = new XMLOutputter(format);
            op.output(doc, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String from, String host, String msg, LogType logType) throws IOException, JDOMException {
        InetAddress inet = InetAddress.getByName(host);
        if (host.isEmpty() || inet.isLoopbackAddress()) {
            this.host = InetAddress.getLocalHost().getHostName();
            this.ip = InetAddress.getLocalHost().getHostAddress();
        } else {
            this.host = inet.getHostName();
            this.ip = inet.getHostAddress();
        }
        this.from = from;
        this.msg = msg;
        this.logtype = logType;
        writeLog();
    }

    public Exception log(String from, String msg, LogType logType) {
        try {
            this.host = InetAddress.getLocalHost().getHostName();
            this.ip = InetAddress.getLocalHost().getHostAddress();
            this.from = from;
            this.msg = msg;
            this.logtype = logType;
            writeLog();
        } catch (Exception e) {
            return e;
        }
        return null;
    }

}
