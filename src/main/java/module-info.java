/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This is the log module. it contains the TCLog and all its functions.
 * However, there is also the possibility of integrating the TCLog into other projects.
 *
 *
 */
module thecat.TCLog {
    requires org.fusesource.jansi;
    requires JCDP;
    requires java.base;
    requires java.se;
    requires thecat.TCLib;
    requires jdom2;
    requires java.desktop;
    exports de.tc.cat.the.tclog.export;
}