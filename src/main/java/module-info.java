/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This is the log module. it contains the TCLog and all its functions.
 * However, there is also the possibility of integrating the TCLog into other projects.
 *
 * @uses de.tc.cat.the.tclog.extern.ReadMCLog from login for Minecraft logfiles.
 */
module de.tc.cat.the.TCLog {
    requires org.fusesource.jansi;
    requires JCDP;
    requires java.base;
    requires java.se;
    requires zip4j;
    requires de.tc.cat.the.TCLib;
    requires jdom2;
    requires java.sql;
    exports de.tc.cat.the.tclog.export;
    uses de.tc.cat.the.tclog.extern.ReadMCLog;
}