/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog;

import de.tc.cat.the.tclog.export.ChckLogType;
import de.tc.cat.the.tclog.export.LogType;
import de.tc.cat.the.util.ConsoleColorOut;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

public class XMLReader {

    private static boolean logtype = false;
    private static boolean error = false;
    private static boolean warning = false;
    private static boolean info = false;
    private static boolean debug = false;
    private static boolean unkonw = false;


    public static void read(File f) throws JDOMException, IOException {
        Document doc = new SAXBuilder().build(f);
        for (Element elm : doc.getRootElement().getChildren()) {
            out(elm);
        }
    }

    private static boolean checkLogType(Attribute att) {
        return att.getName().equals("LogType");
    }

    private static boolean checkError(String logtype) {
        return ChckLogType.check(logtype) == LogType.Error;
    }

    private static boolean checkWarning(String logtype) {
        return ChckLogType.check(logtype) == LogType.Warning;
    }

    private static boolean checkDebug(String logtype) {
        return ChckLogType.check(logtype) == LogType.Debug;
    }

    private static boolean checkInfo(String logtype) {
        return ChckLogType.check(logtype) == LogType.Info;
    }

    private static boolean checkUnknow(String logtype) {
        return ChckLogType.check(logtype) == LogType.Unknow;
    }

    private static void out(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            if (!checkLogType(att)) {
                continue;
            } else {
                logtype = checkLogType(att);
                error = checkError(att.getValue());
                info = checkInfo(att.getValue());
                warning = checkWarning(att.getValue());
                debug = checkDebug(att.getValue());
                unkonw = checkUnknow(att.getValue());
            }
        }

        if (logtype && error) {
            outError(elm);
        } else if (logtype && warning) {
            outWarning(elm);
        } else if (logtype && info) {
            outInfo(elm);
        } else if (logtype && debug) {
            outDebug(elm);
        } else if (logtype && unkonw) {
            outUnknow(elm);
        } else {
            ConsoleColorOut.printlnInfo("Unknow LogMessage.");
        }
        error = false;
        warning = false;
        debug = false;
        info = false;
        unkonw = false;
        logtype = false;
    }

    private static void outError(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            ConsoleColorOut.printlnError(att.getName() + ": " + att.getValue());
        }
    }

    private static void outDebug(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            ConsoleColorOut.printlnDebug(att.getName() + ": " + att.getValue());
        }
    }

    private static void outInfo(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            ConsoleColorOut.printlnInfo(att.getName() + ": " + att.getValue());
        }
    }

    private static void outWarning(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            ConsoleColorOut.printlnWarning(att.getName() + ": " + att.getValue());
        }
    }

    private static void outUnknow(Element elm) {
        for (Attribute att : elm.getAttributes()) {
            ConsoleColorOut.printlnUnknow(att.getName() + ": " + att.getValue());
        }
    }
}
