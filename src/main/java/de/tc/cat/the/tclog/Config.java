/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static de.tc.cat.the.tclog.StaticVariable.*;

public class Config {
    private static final Properties conf = new Properties();

    public static void saveConfig(File f, String key, String value, String comment) throws IOException {
        conf.setProperty(key, value);
        conf.storeToXML(new FileOutputStream(f), comment);
    }

    public static String loadConfig(File f, String key) throws IOException {
        conf.loadFromXML(new FileInputStream(f));
        return conf.getProperty(key);
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

        saveConfig(StaticVariable.conf, bgcolor, "#" + gr, "");
        saveConfig(StaticVariable.conf, fgcolor, "#" + bl, "");
        saveConfig(StaticVariable.conf, ercolor, "#" + re, "");
        saveConfig(StaticVariable.conf, wacolor, "#" + yl, "");
        saveConfig(StaticVariable.conf, incolor, "#" + bl, "");
        saveConfig(StaticVariable.conf, uncolor, "#" + cb, "");
        saveConfig(StaticVariable.conf, decolor, "#" + mg, "");
        saveConfig(StaticVariable.conf, excolor, "#" + re, "");
        saveConfig(StaticVariable.conf, linkColor, "#" + hb, "");
        saveConfig(StaticVariable.conf, unlinkColor, "#" + hb, "");

    }
}
