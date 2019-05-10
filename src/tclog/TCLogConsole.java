/*
 * Copyright (C) 2019 the-c.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package tclog;

import de.tc.cat.the.system.Time;

/**
 *
 * @author the-c
 */
public class TCLogConsole {
    private static final Tabellog t = new Tabellog();
    private static final Time tt = new Time();
    public void Arguments(String[] args)
    {
        String arg1 = args[0].replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", "");
        String arg2 = args[1].replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", "");
        
        if (arg1.equals("console") && arg2.equals("--read") || arg2.equals("-r"))
                {
                    readConsole();
                }
        else if (arg1.equals("console") && arg2.equals("--info") || arg2.equals("-i"))
        {
            outInfo();
        }
        else if(arg1.equals("console") && arg2.equals("--warning") || arg2.equals("-w"))
        {
            outWarning();
        }
        else if (arg1.equals("console") && arg2.equals("--error") || arg2.equals("-e"))
        {
            outError();
        }
        else if(arg1.equals("console") && arg2.equals("--mix") || arg2.equals("-m"))
        {
            outDev();
        }
        else
        {
            t.log(tt.getDate(), tt.getTime(), "TCLog", "Error", "Illegal Arguments for TCLog. Error 2");
        }
    }
    
    private static void readConsole()
    {
        for (int i = 0; !t.readDatbase().isEmpty() && i < t.readDatbase().size(); i++)
        {
            if (t.readDatbase().get(i).contains("Error"))
            {
                System.err.print(t.readDatbase().get(i) + "\n");
            }
            else
            {
                System.out.print(t.readDatbase().get(i) + "\n");
            }
        }
    }
    
    private static void outInfo()
    {
        for (int i = 0; !t.readDatbase().isEmpty() && i < t.readDatbase().size(); i++)
        {
            if (t.readDatbase().get(i).contains("Info"))
            {
                System.out.print(t.readDatbase().get(i) + "\n");
            }
        }
    }
    
    private static void outWarning()
    {
        for (int i = 0; !t.readDatbase().isEmpty() && i < t.readDatbase().size(); i++)
        {
            if (t.readDatbase().get(i).contains("Warning"))
            {
                System.out.print(t.readDatbase().get(i) + "\n");
            }
        }
    }
    
    private static void outError()
    {
        for (int i = 0; !t.readDatbase().isEmpty() && i < t.readDatbase().size(); i++)
        {
            if (t.readDatbase().get(i).contains("Error"))
            {
                System.err.print(t.readDatbase().get(i) + "\n");
            }
        }
    }
    
    private static void outDev()
    {
        for (int i = 0; !t.readDatbase().isEmpty() && i < t.readDatbase().size(); i++)
        {
            if (!t.readDatbase().get(i).contains("Info") || !t.readDatbase().get(i).contains("Warning") || !t.readDatbase().get(i).contains("Error"))
            {
                System.out.print(t.readDatbase().get(i) + "\n");
            }
        }
    }
}
