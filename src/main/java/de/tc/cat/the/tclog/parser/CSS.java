package de.tc.cat.the.tclog.parser;

import de.tc.cat.the.tclog.Config;
import de.tc.cat.the.tclog.export.LogType;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static de.tc.cat.the.tclog.StaticVariable.*;

public class CSS {

    public static void CSS() throws IOException {
        if (!new File(homeCSS + sep + "html" + sep + "css" + sep).exists()) {
            new File(homeCSS + sep + "html" + sep + "css" + sep).mkdirs();
        }
        File style = new File(homeCSS + sep + "html" + sep + "css" + sep + "style.css");
        if (style.exists()) {
            style.deleteOnExit();
        }
        style.createNewFile();
        PrintStream psStyle = new PrintStream(style);
        psStyle.println("." + LogType.Unknow.name() + "{");
        psStyle.println("\tcolor: " + Config.loadConfig(conf, uncolor));
        psStyle.println("}");
        psStyle.println("." + LogType.Error.name() + "{");
        psStyle.println("\tcolor: " + Config.loadConfig(conf, ercolor));
        psStyle.println("}");
        psStyle.println("." + LogType.Debug.name() + "{");
        psStyle.println("\tcolor: " + Config.loadConfig(conf, decolor));
        psStyle.println("}");
        psStyle.println("." + LogType.Warning.name() + "{");
        psStyle.println("\tcolor: " + Config.loadConfig(conf, wacolor));
        psStyle.println("}");
        psStyle.println("." + LogType.Info.name() + "{");
        psStyle.println("\tcolor: " + Config.loadConfig(conf, incolor));
        psStyle.println("}");
        psStyle.println(".exception {");
        psStyle.println("\tcolor: " + Config.loadConfig(conf, excolor));
        psStyle.println("}");
        psStyle.println("html {");
        psStyle.println("\tbackground: " + Config.loadConfig(conf, bgcolor));
        psStyle.println("}");
        psStyle.flush();
        psStyle.close();
    }
}
