package de.tc.cat.the.tclog.export;

import de.tc.cat.the.sql.*;
import de.tc.cat.the.system.*;
import de.tc.cat.the.tclog.TCLogARGS1;
import static de.tc.cat.the.tclog.TCLogConsoleInput.ExeceptionFilter;
import de.tc.cat.the.tclog.Tabellog;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author the-c
 */
public class TCLog {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SQLException, IOException {
        // TODO code application logic here
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
        Tabellog table = new Tabellog();
        
        switch(args.length)
        {
            case 3:
                table.log(args);
                break;
            case 5:
                table.log(args);
                break;
            case 1:
                TCLogARGS1.args1(args);
            default:
                ExeceptionFilter();
                break;
                
        }
    }
    
}
