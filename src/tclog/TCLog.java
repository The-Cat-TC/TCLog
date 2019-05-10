package tclog;

import de.tc.cat.the.sql.*;
import de.tc.cat.the.system.*;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author the-c
 */
public class TCLog {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        TCLogConsole tclc = new TCLogConsole();
        SQLLite sqlite = new SQLLite();
        String Path = "";
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
        Connection conn = sqlite.connectetDatebase(Path, Name);
        sqlite.createTabel(Path, Name, sql);
        conn.close();
        Tabellog table = new Tabellog();
        Time t = new Time();
        
        switch(args.length)
        {
            case 2:
                tclc.Arguments(args);
                break;
            case 3:
                table.log(args);
                break;
            case 5:
                table.log(args);
                break;
            default:
                table.log(t.getDate(), t.getTime(), "TCLog", "Info", "TCLog gestartet. Code 0");
                TCLogView.main(args);
                break;
                
        }
    }
    
}
