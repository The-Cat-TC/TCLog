package de.tc.cat.the.tclog;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import static de.tc.cat.the.tclog.StaticVariable.cp;

public class ReadConsole {

    public static void err(String msg) {
        cp = new ColoredPrinter.Builder(3, false)
                .foreground(Ansi.FColor.RED)
                .background(Ansi.BColor.BLACK)
                .build();
        cp.println(msg);
    }

    public static void war(String msg) {
        cp = new ColoredPrinter.Builder(2, false)
                .foreground(Ansi.FColor.YELLOW)
                .background(Ansi.BColor.BLACK)
                .build();
        cp.println(msg);
    }

    public static void inf(String msg) {
        cp = new ColoredPrinter.Builder(1, false)
                .foreground(Ansi.FColor.WHITE)
                .background(Ansi.BColor.BLACK)
                .build();
        cp.println(msg);
    }

    public static void deb(String msg) {
        cp = new ColoredPrinter.Builder(4, false)
                .foreground(Ansi.FColor.CYAN)
                .background(Ansi.BColor.BLACK)
                .build();
        cp.println(msg);
    }

    public static void unk(String msg) {
        cp = new ColoredPrinter.Builder(0, false)
                .foreground(Ansi.FColor.MAGENTA)
                .background(Ansi.BColor.BLACK)
                .build();
        cp.println(msg);
    }
}
