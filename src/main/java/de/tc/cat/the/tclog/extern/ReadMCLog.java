package de.tc.cat.the.tclog.extern;

import de.tc.cat.the.exception.FileTypeException;
import de.tc.cat.the.system.GZip;
import de.tc.cat.the.tclog.LogTypeConverter;
import de.tc.cat.the.tclog.export.TCLog;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReadMCLog {
    private static String Time = "";

    public static void readLog(File f) throws IOException, FileTypeException, ParseException {
        if (f.getName().endsWith(".gz")) {
            readZip(f);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Time = sdf.format(f.lastModified());
            readfile(f);
        }

    }

    private static void readfile(File f) throws IOException {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String zeile = br.readLine();
        while (zeile != null) {
            writeLog(zeile);
            zeile = br.readLine();
        }
    }

    private static void writeLog(String s) {
        if (!s.startsWith("[")) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String v = s.replaceAll("[_[^\\w\\däöüÄÖÜ.:+  \\- /]]", "");
        sb.append(v);
        sb.replace(sb.indexOf(" "), sb.indexOf("/") + 1, " ");
        sb.insert(sb.indexOf(":", 8), " \"");
        sb.insert(sb.length(), "\"");
        sb.deleteCharAt(sb.indexOf(":", 7));
        sb.deleteCharAt(sb.indexOf("\"") + 1);
        String[] args = sb.toString().split(" ", 4);
        LogTypeConverter logtype = new LogTypeConverter();
        TCLog.main(new String[]{Time, args[0], args[2], logtype.logType(args[1]), args[3]});

    }

    private static void readZip(File f) throws IOException, FileTypeException, ParseException {
        Time = f.getName().substring(0, f.getName().lastIndexOf("-"));
        GZip.entpacken(f);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Time = sdf.format(sdf2.parseObject(Time));
        File file = new File(f.getName().substring(0, f.getName().lastIndexOf(".") - 1));
        readfile(f);
        file.delete();
    }
}
