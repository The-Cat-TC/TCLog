package de.tc.cat.the.tclog;

import de.tc.cat.the.util.Configuration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static de.tc.cat.the.tclog.StaticVariable.*;

public class Config {
    private static final Configuration conf = new Configuration();

    public static void saveConfig(File f, String key, String value, boolean save) throws IOException {
        conf.saveSetting(f, key, value, "", save);
    }

    public static String loadConfig(File f, String key) throws IOException {

        Configuration conf = new Configuration();
        return conf.loadSetting(f, key);
    }

    protected static void firstConfig() throws IOException {
        if (StaticVariable.conf.exists()) {
            return;
        }
        String gr = Integer.toHexString(color(new Color(191, 191, 191)).getRGB()).substring(2).replaceAll("[_[^\\w\\d]]", "");
        String re = Integer.toHexString(color(new Color(204, 51, 0)).getRGB()).substring(2).replaceAll("[_[^\\w\\d]]", "");
        String yl = Integer.toHexString(color(new Color(204, 255, 51)).getRGB()).substring(2).replaceAll("[_[^\\w\\d]]", "");
        String bl = Integer.toHexString(color(new Color(0, 0, 0)).getRGB()).substring(2).replaceAll("[_[^\\w\\d]]", "");
        String mg = Integer.toHexString(color(new Color(198, 26, 255)).getRGB()).substring(2).replaceAll("[_[^\\w\\d]]", "");
        String cb = Integer.toHexString(color(new Color(77, 121, 255)).getRGB()).substring(2).replaceAll("[_[^\\w\\d]]", "");
        String hb = Integer.toHexString(color(new Color(102, 179, 255)).getRGB()).substring(2).replaceAll("[_[^\\w\\d]]", "");

        saveConfig(StaticVariable.conf, bgcolor, "#" + gr, true);
        saveConfig(StaticVariable.conf, fgcolor, "#" + bl, true);
        saveConfig(StaticVariable.conf, ercolor, "#" + re, true);
        saveConfig(StaticVariable.conf, wacolor, "#" + yl, true);
        saveConfig(StaticVariable.conf, incolor, "#" + bl, true);
        saveConfig(StaticVariable.conf, uncolor, "#" + cb, true);
        saveConfig(StaticVariable.conf, decolor, "#" + mg, true);
        saveConfig(StaticVariable.conf, excolor, "#" + re, true);
        saveConfig(StaticVariable.conf, linkColor, "#" + hb, true);
        saveConfig(StaticVariable.conf, unlinkColor, "#" + hb, true);

    }
}
