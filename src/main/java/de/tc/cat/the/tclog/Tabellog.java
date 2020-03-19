/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tc.cat.the.tclog;

import de.tc.cat.the.sql.SQLLite;
import de.tc.cat.the.system.Seperator;
import de.tc.cat.the.system.Time;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author the-c
 */
public class Tabellog {
    String s = Seperator.fileseperator();
    String dir = System.getProperty("user.home") + s + ".TC" + s;
    public void log (String datum, String zeit, String application, String type, String meldung)
    {
        SQLLite sqll = new SQLLite();
        Connection connn = sqll.connectetDatebase(dir, "TCLog");
        String sql = "INSERT INTO Log(Datum,Zeit,Application,Type,Meldung) VALUES(?,?,?,?,?)";
        try (Connection conn = connn; PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setString(1, datum);
            pstmt.setString(2, zeit);
            pstmt.setString(3, application);
            pstmt.setString(4, type);
            pstmt.setString(5, meldung);
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.print(e.getMessage() + "\n");
        }
    }
    public void log (String[] args)
    {
        Time t = new Time();
        SQLLite sqll = new SQLLite();
        Connection connn = sqll.connectetDatebase(dir, "TCLog");
        String sql = "INSERT INTO Log(Datum,Zeit,Application,Type,Meldung) VALUES(?,?,?,?,?)";
        try (Connection conn = connn; PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            if (args.length == 3)
            {
                pstmt.setString(1, Time.getDate());
                pstmt.setString(2, Time.getTime());
                pstmt.setString(3, args[0].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.setString(4, args[1].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.setString(5, args[2].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.executeUpdate();
            }
            else if (args.length == 5)
            {
                pstmt.setString(1, args[0].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.setString(2, args[1].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.setString(3, args[2].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.setString(4, args[3].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.setString(5, args[4].replaceAll("[_[^\\w\\däüöÄÜÖ.:\\+\\- ]]", ""));
                pstmt.executeUpdate();
            }
            
        }
        catch (SQLException e)
        {
            System.err.print(e.getMessage() + "\n");
        }
    }
    
    /*public DefaultTableModel readTable()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        SQLLite sql = new SQLLite();
        Connection conn = sql.connectetDatebase(dir, "TCLog");
        String sqll = "SELECT Id, Datum, Zeit, Application, Type, Meldung FROM Log";

        ArrayList<Integer> Id = new ArrayList<>();

        ArrayList<String> Datum = new ArrayList<>();

        ArrayList<String> Zeit = new ArrayList<>();

        ArrayList<String> Application = new ArrayList<>();

        ArrayList<String> Type = new ArrayList<>();

        ArrayList<String> Meldung = new ArrayList<>();
        try (Connection conn2 = conn;
             Statement stmt  = conn2.createStatement();
             ResultSet rs    = stmt.executeQuery(sqll)){
            
            // loop through the result set
            while (rs.next()) {
                Id.add(rs.getInt("Id"));
                Datum.add(rs.getString("Datum"));
                Zeit.add(rs.getString("Zeit"));
                Application.add(rs.getString("Application"));
                Type.add(rs.getString("Type"));
                Meldung.add(rs.getString("Meldung"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        dtm.addColumn("Id", Id.toArray());
        dtm.addColumn("Datum", Datum.toArray());
        dtm.addColumn("Zeit", Zeit.toArray());
        dtm.addColumn("Application", Application.toArray());
        dtm.addColumn("Type", Type.toArray());
        dtm.addColumn("Meldung", Meldung.toArray());
        return dtm;
    }*/
    
    public ArrayList<String> readDatbase()
    {
        ArrayList<String> list = new ArrayList<>();
        SQLLite sql2 = new SQLLite();
        String sql = "SELECT Id, Datum, Zeit, Application, Type, Meldung FROM Log";
        try (Connection conn = sql2.connectetDatebase(dir, "TCLog");
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                list.add(rs.getInt("Id") +  "\t" + 
                                   rs.getString("Datum") + "\t" +
                                   rs.getString("Zeit") + "\t" +
                                   rs.getString("Application") + "\t" +
                                   rs.getString("Type") + "\t" +
                                   rs.getString("Meldung"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void deleteDate()
    {
        SQLLite sqll = new SQLLite();
        Connection connn = sqll.connectetDatebase(dir, "TCLog");
        String sql = "DELETE FROM Log WHERE Id = ?";
        String sql2 = "SELECT Id FROM Log";
        try (Connection conn = connn; PreparedStatement pstmt = conn.prepareStatement(sql);Statement stmt  = conn.createStatement();ResultSet rs    = stmt.executeQuery(sql2);)
        {
            while (rs.next())
            {
                pstmt.setInt(1, rs.getInt("Id"));
                pstmt.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            System.err.print(e.getMessage() + "\n");
        }
    }
}
