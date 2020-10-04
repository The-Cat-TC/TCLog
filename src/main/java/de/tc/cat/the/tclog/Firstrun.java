package de.tc.cat.the.tclog;

import de.tc.cat.the.sql.SQLLite;
import de.tc.cat.the.system.Seperator;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Firstrun {
    public static void create() throws SQLException {
        SQLLite sqlite = new SQLLite();
        String s = Seperator.fileseperator();
        String dir = System.getProperty("user.home") + s + ".TC" + s;
        String Path = dir;
        File path = new File(Path);
        if (!path.exists())
        {
            path.mkdirs();
        }
        String Name = "TCLog";
        String sql = "CREATE TABLE IF NOT EXISTS Log (\n"
                + " Id integer PRIMARY KEY,\n"
                + " Datum  text NOT NULL, \n"
                + " Zeit text NOT NULL, \n"
                + " Application text NOT NULL, \n"
                + " Type text NOT NULL, \n"
                + " Meldung text NOT NULL \n"
                + ");";
        sqlite.createNewDatebase(Path, Name);
        try (Connection conn = sqlite.connectetDatebase(Path, Name)) {
            sqlite.createTabel(Path, Name, sql);
        }
    }
}
