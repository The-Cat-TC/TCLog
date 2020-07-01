package de.tc.cat.the.tclog.export;

import de.tc.cat.the.sql.*;
import de.tc.cat.the.system.*;
import de.tc.cat.the.tclog.Firstrun;
import de.tc.cat.the.tclog.TCLogARGS1;
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
        Firstrun.create();
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
                break;
                
        }
    }
    
}
