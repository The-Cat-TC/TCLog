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
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static de.tc.cat.the.tclog.StaticVariable.sep;

public class XML {

    public static final List<Element> link = new ArrayList<Element>();
    private final String user = System.getProperty("user.name");
    private final String homeLOG = System.getProperty("user.home") + sep + ".TC" + sep + "log";
    private final String logname = "log.tclog";
    private final String date = Time.getDate();
    private final String exceptiondir = "exceptions";
    private final File Exdir = new File(homeLOG + sep + date);
    private final File log = new File(homeLOG + sep + date + sep + logname);
    private ProcessHandle pr;
    private Thread th;
    private String from;
    private String ip;
    private String host;
    private LogType logtype;
    private String msg;
    private Document doc;
    private Document exd;

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
        exd = new Document();
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
        if (!link.isEmpty()) {
            Element ex = new Element("exceptions");
            for (Element elm : link) {
                ex.addContent(elm);
            }
            e1.addContent(ex);
        }
        if (log.exists()) {
            SAXBuilder build = new SAXBuilder();
            doc = build.build(log);
            doc.getRootElement().addContent(e1);
            writeXML(doc, log);
            link.clear();
        } else {
            doc.getRootElement().addContent(e1);
            writeXML(doc, log);
            link.clear();
        }


    }

    private File writeException(Exception ex) {
        exd.setRootElement(new Element(exceptiondir));
        Element e1 = new Element(StringCleaner(ex.toString()));
        for (StackTraceElement ste : ex.getStackTrace()) {
            Element cln = new Element("Class-Name");
            Element cll = new Element("Class-Loader");
            Element fln = new Element("File-Name");
            Element mtn = new Element("Method-Name");
            Element mdn = new Element("Module-Name");
            Element mdv = new Element("Module-Version");
            Element lin = new Element("Line-Number");
            Element bli = new Element("finished-reading-of-exception-in-class");
            cln.setText(ste.getClassName());
            cll.setText(ste.getClassLoaderName());
            fln.setText(ste.getFileName());
            mtn.setText(ste.getMethodName());
            mdn.setText(ste.getModuleName());
            mdv.setText(ste.getModuleVersion());
            lin.setText(String.valueOf(ste.getLineNumber()));
            e1.addContent(cln);
            e1.addContent(cll);
            e1.addContent(fln);
            e1.addContent(mtn);
            e1.addContent(mdn);
            e1.addContent(mdv);
            e1.addContent(lin);
            e1.addContent(bli);
        }
        exd.getRootElement().addContent(e1);
        int i = 0;
        File exn = new File(Exdir + sep + StringCleaner(ex.toString()) + ".exception" + i);
        while (exn.exists()) {
            exn = new File(Exdir + sep + StringCleaner(ex.toString()) + ".exception" + i);
            if (!exn.exists()) {
                break;
            }
            i = i + 1;

        }
        writeXML(exd, exn);
        return exn;

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
        } catch (UnknownHostException e) {
            return e;
        } catch (IOException e) {
            return e;
        } catch (JDOMException e) {
            return e;
        }
        return null;
    }

    public void addException(Exception ex) {
        Element elm = new Element(writeException(ex).getName());
        link.add(elm);
    }

    private String StringCleaner(String string) {
        String s;
        if (string.indexOf(" ") == -1 || string.indexOf(":") == -1) {
            s = string;
        } else if (string.indexOf(":") != -1) {
            s = string.substring(0, string.indexOf(":"));
        } else if (string.indexOf(" ") != -1) {
            s = string.substring(0, string.indexOf(" "));
        } else {
            s = string;
        }
        return s;
    }

}
