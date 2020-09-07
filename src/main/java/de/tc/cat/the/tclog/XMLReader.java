package de.tc.cat.the.tclog;

import de.tc.cat.the.system.FileRecursive;
import de.tc.cat.the.system.Seperator;
import de.tc.cat.the.tclog.export.Log;
import de.tc.cat.the.tclog.export.LogType;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    private final List<Element> error = new ArrayList<>();
    private final List<Element> warning = new ArrayList<>();
    private final List<Element> info = new ArrayList<>();
    private final List<Element> debug = new ArrayList<>();
    private final List<Element> unknow = new ArrayList<>();
    private final String sep = Seperator.fileseperator();
    private final String home = System.getProperty("user.home") + sep + ".TC" + sep + "log";
    private final File log = new File(home + sep);

    public XMLReader(File f) {
        try {
            if (!f.exists()) {
                throw new FileNotFoundException(f.getAbsolutePath() + "is not exist.");
            }
            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(f);

            for (Element elm : doc.getRootElement().getChildren()) {
                if (elm.getName().equals("log")) {
                    checkType(elm);
                }
            }
        } catch (Exception ex) {
            Log.addException(ex);
        }
    }

    public XMLReader() {
        try {
            FileRecursive fr = new FileRecursive(log);
            for (String s : fr.file()) {
                File f = new File(s);
                if (!f.exists()) {
                    throw new FileNotFoundException(f.getAbsolutePath() + "is not exist.");
                }
                SAXBuilder sax = new SAXBuilder();
                Document doc = sax.build(f);

                for (Element elm : doc.getRootElement().getChildren()) {
                    if (elm.getName().equals("log")) {
                        checkType(elm);
                    }
                }
            }
        } catch (Exception ex) {
            Log.addException(ex);
        }
    }

    private void checkType(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            if (att.getName().equals("LogType")) {
                if (att.getValue().equals(LogType.Error.name())) {
                    error.add(elm);
                } else if (att.getValue().equals(LogType.Warning.name())) {
                    warning.add(elm);
                } else if (att.getValue().equals(LogType.Info.name())) {
                    info.add(elm);
                } else if (att.getValue().equals(LogType.Debug.name())) {
                    debug.add(elm);
                } else {
                    unknow.add(elm);
                }
            }
        }
    }

    public void getDebug() {
        for (Element elm : debug) {
            for (Attribute att : elm.getAttributes()) {
                ReadConsole.deb(att.getName() + ": " + att.getValue());
            }
        }
    }

    public void getError() {
        for (Element elm : error) {
            for (Attribute att : elm.getAttributes()) {
                ReadConsole.err(att.getName() + ": " + att.getValue());
            }
        }
    }

    public void getInfo() {
        for (Element elm : info) {
            for (Attribute att : elm.getAttributes()) {
                ReadConsole.inf(att.getName() + ": " + att.getValue());
            }
        }
    }

    public void getUnknow() {
        for (Element elm : unknow) {
            for (Attribute att : elm.getAttributes()) {
                ReadConsole.unk(att.getName() + ": " + att.getValue());
            }
        }
    }

    public void getWarning() {
        for (Element elm : warning) {
            for (Attribute att : elm.getAttributes()) {
                ReadConsole.war(att.getName() + ": " + att.getValue());
            }
        }
    }
}
