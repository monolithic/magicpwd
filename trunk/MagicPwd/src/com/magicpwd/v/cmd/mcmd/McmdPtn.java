/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.v.cmd.mcmd;

/**
 * 命令行模式
 * @author Aven
 */
public class McmdPtn
{

    public McmdPtn()
    {
    }

    public void init()
    {
        console = System.console();
        if (console != null)
        {
            return;
        }
        scanner = new java.util.Scanner(System.in);
    }

    public boolean endSave()
    {
        return true;
    }

    public void print(String cmd)
    {
        System.out.print(cmd);
    }

    public void printLine(String cmd)
    {
        System.out.println(cmd);
    }

    public String readLine()
    {
        if (console != null)
        {
            return console.readLine();
        }
        return scanner.next();
    }

    public String readPassword()
    {
        if (console != null)
        {
            return new String(console.readPassword());
        }
        return scanner.next();
    }

    public Integer readInt()
    {
        return scanner.nextInt();
    }

    public Long readLong()
    {
        return scanner.nextLong();
    }

    public Double readDouble()
    {
        return scanner.nextDouble();
    }
    private java.io.Console console;
    private java.util.Scanner scanner;
}
