package tool.dat.ico;

/*
 *  Copyright (C) 2011 yaoshangwen
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
import java.text.SimpleDateFormat;

/**
 *
 * @author yaoshangwen
 */
public class IcoName
{

    private static SimpleDateFormat format;

    public static void main(String[] args)
    {
        try
        {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.YEAR, 2011);
            cal.set(java.util.Calendar.MONTH, 1);
            cal.set(java.util.Calendar.DAY_OF_MONTH, 23);
            cal.set(java.util.Calendar.HOUR_OF_DAY, 14);
            cal.set(java.util.Calendar.MINUTE, 20);
            cal.set(java.util.Calendar.SECOND, 1);
            format = new SimpleDateFormat("yyyyMMddHHmmss");
            renameTo(new java.io.File("dat/ico"), new java.io.File("tmp/ico"), cal);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }

    private static void renameTo(java.io.File src, java.io.File dst, java.util.Calendar cal)
    {
        for (java.io.File tmp : src.listFiles())
        {
            if (tmp.isFile())
            {
                if (tmp.getName().toUpperCase().endsWith(".PNG"))
                {
                    tmp.setLastModified(cal.getTimeInMillis());
                    tmp.renameTo(new java.io.File(dst, "AM" + format.format(cal.getTime()) + "_16.PNG"));
                    cal.add(java.util.Calendar.SECOND, 1);
                }
                continue;
            }
            java.io.File dir = new java.io.File(dst, tmp.getName());
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            renameTo(tmp, dir, cal);
        }
    }
}
