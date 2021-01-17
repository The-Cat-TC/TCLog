package de.tc.cat.the.tclog;

import com.diogonunes.jcdp.color.ColoredPrinter;
import de.tc.cat.the.system.Seperator;
import de.tc.cat.the.tclog.parser.XML;

import java.awt.*;
import java.io.File;

public class StaticVariable {
    public static final String sep = Seperator.fileseperator();
    public static final String homeHTML = System.getProperty("user.home") + sep + ".TC" + sep + "log";
    public static final File log = new File(homeHTML + sep);
    public static final String homeCONF = System.getProperty("user.home") + sep + ".TC" + sep + "config" + sep;
    public static final String bgcolor = "bgcolor";
    public static final String ercolor = "ercolor";
    public static final String wacolor = "wacolor";
    public static final String decolor = "decolor";
    public static final String incolor = "incolor";
    public static final String uncolor = "uncolor";
    public static final String excolor = "excolor";
    public static final String fgcolor = "fgcolor";
    public static final String linkColor = "linkcolor";
    public static final String unlinkColor = "unlinkcolor";
    public static File conf = new File(homeCONF + sep + "tclog.tcconf");
    public static File confDir = new File(homeCONF);
    public static XML xml = new XML();
    public static ColoredPrinter cp;

    public static Color color(SystemColor scolor) {
        return scolor;
    }

    public static Color color(Color color) {
        return color;
    }
}
