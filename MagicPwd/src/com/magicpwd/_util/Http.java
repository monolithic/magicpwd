/*
 *  Copyright (C) 2011 Amon
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
package com.magicpwd._util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 *
 * @author Amon
 */
public class Http
{

    public static InputStream post(HttpURLConnection con, HashMap<String, String> map) throws Exception
    {
        con.setDoOutput(true);// 打开写入属性
        con.setDoInput(true);// 打开读取属性
        con.setRequestMethod("POST");// 设置提交方法
        con.setConnectTimeout(50000);// 连接超时时间
        con.setReadTimeout(50000);
        con.connect();

        StringBuilder buf = new StringBuilder();
        for (String key : map.keySet())
        {
            buf.append('&').append(key).append('=').append(map.get(key));
        }

        OutputStream out = con.getOutputStream();
        out.write(buf.replace(0, 1, "?").toString().getBytes());
        out.flush();
        out.close();

        return con.getInputStream();
        //读取post之后的返回值
        //BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        //String line = null;
        //StringBuilder sb = new StringBuilder();
        //while ((line = in.readLine()) != null)
        //{
        //    sb.append(line);
        //}
        //in.close();
        //
        //con.disconnect();//断开连接
    }
}
