/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module de.tc.cat.the.tclog {
    requires org.fusesource.jansi;
    requires JCDP;
    requires java.base;
    requires java.se;
    requires zip4j;
    requires de.tc.cat.the.TCLib;
    
    exports de.tc.cat.the.tclog.export;
    uses de.tc.cat.the.tclog.export.TCLog;
}