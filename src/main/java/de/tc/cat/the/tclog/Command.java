/*
 * Copyright (c) 2018 - 2020 The Cat.
 */

package de.tc.cat.the.tclog;

import de.tc.cat.the.tclog.parser.HTML;

public class Command {
    private String[] args;
    public Command(String[] args) {
        this.args = args;
    }

    public void cmd() throws Exception {
        if (args[0].equals("--export-html")) {
            HTML.HTML();
        }
    }
}
