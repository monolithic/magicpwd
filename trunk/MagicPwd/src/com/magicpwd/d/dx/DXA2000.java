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
package com.magicpwd.d.dx;

import com.magicpwd.d.db.DBA3000;
import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.mpwd.Mkey;
import com.magicpwd.__a.AEditItem;
import com.magicpwd._comn.item.GuidItem;
import com.magicpwd._comn.item.HintItem;
import com.magicpwd._comn.item.LogoItem;
import com.magicpwd._comn.item.MetaItem;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Logs;
import com.magicpwd.d.db.DBAccess;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Amon
 */
public class DXA2000 extends DXA
{

    public DXA2000()
    {
    }

    @Override
    public int importByKind(UserMdl userMdl, SafeMdl safeMdl, java.util.ArrayList<java.util.ArrayList<String>> data, String kindHash) throws Exception
    {
        int size = 0;
        int indx = 0;
        IEditItem item;
        Mkey tempKeys = new Mkey();
        java.util.ArrayList<IEditItem> tempList = new java.util.ArrayList<IEditItem>();
        DBAccess dba = new DBAccess();
        dba.init();
        for (java.util.ArrayList<String> temp : data)
        {
            switch ((temp.size() - 8) % 3)
            {
                case 2:
                    if (com.magicpwd._util.Char.isValidate(temp.get(temp.size() - 1)))
                    {
                        temp.add("");
                        break;
                    }
                    temp.remove(temp.size() - 1);
                case 1:
                    temp.remove(temp.size() - 1);
                    break;
            }

            indx = 0;
            tempKeys.setDefault();
            tempList.clear();
            tempKeys.setP30F0105(userMdl.getCode());

            // Guid
            GuidItem guid = new GuidItem(userMdl);
            String date = temp.get(indx++);
            if (!Char.isValidateDate(date, true))
            {
                return size;
            }
            guid.setName(date);
            guid.setData(kindHash);
            guid.setSpec(GuidItem.SPEC_GUID_TPLT, temp.get(indx++));
            tempList.add(guid);

            // Meta
            MetaItem meta = new MetaItem(userMdl);
            meta.setName(temp.get(indx++));
            meta.setData(temp.get(indx++));
            tempList.add(meta);

            // Logo
            LogoItem logo = new LogoItem(userMdl);
            tempList.add(logo);
            logo.setName(temp.get(indx++));
            logo.setData(temp.get(indx++));

            // Hint
            HintItem hint = new HintItem(userMdl);
            String text = temp.get(indx++);
            if (com.magicpwd._util.Char.isValidate(text))
            {
                //hint.setTime(new java.sql.Timestamp(com.magicpwd._util.Date.toDate(text, '-', ':', ' ').getTimeInMillis()));
            }
            hint.setName(temp.get(indx++));
            tempList.add(hint);

            try
            {
                while (indx < temp.size())
                {
                    item = AEditItem.getInstance(userMdl, Integer.parseInt(temp.get(indx++)));
                    item.setName(temp.get(indx++));
                    item.setData(temp.get(indx++));
                    tempList.add(item);
                }
                safeMdl.enCrypt(tempKeys, tempList);
                DBA3000.savePwdsData(dba, tempKeys);
                size += 1;
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }

        dba.dispose();
        return size;
    }

    @Override
    public int importByKeys(java.util.ArrayList<java.util.ArrayList<String>> data, String kindHash) throws Exception
    {
        return 0;
    }

    @Override
    public int exportByKind(UserMdl userMdl, SafeMdl safeMdl, java.util.ArrayList<java.util.ArrayList<String>> data, String kindHash) throws Exception
    {
        java.util.ArrayList<Mkey> dataList = new java.util.ArrayList<Mkey>();
        DBA3000.readKeysList(userMdl, kindHash, dataList);
        if (dataList == null || dataList.size() < 1)
        {
            return 0;
        }

        int size = 0;
        java.util.ArrayList<String> temp;
        int indx;
        IEditItem item;

        java.util.ArrayList<IEditItem> tempList = new java.util.ArrayList<IEditItem>();
        for (Mkey keys : dataList)
        {
            indx = 0;
            temp = new java.util.ArrayList<String>();
            try
            {
                tempList.clear();
                keys.setP30F0105(userMdl.getCode());
                keys.getPassword().setDefault();
                if (DBA3000.readPwdsData(keys))
                {
                    safeMdl.deCrypt(keys, tempList);
                }
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                continue;
            }

            // Guid
            item = tempList.get(indx++);
            temp.add(item.getName());
            temp.add(item.getSpec(GuidItem.SPEC_GUID_TPLT));

            // Meta
            item = tempList.get(indx++);
            temp.add(item.getName());
            temp.add(item.getData());

            // Logo
            item = tempList.get(indx++);
            temp.add(item.getName());
            temp.add(item.getData());

            // Hint
            item = tempList.get(indx++);
            temp.add(item.getData());
            temp.add(item.getName());

            while (indx < tempList.size())
            {
                item = tempList.get(indx++);
                temp.add("" + item.getType());
                temp.add(item.getName());
                temp.add(item.getData());
            }

            size += 1;
            data.add(temp);
        }
        return size;
    }

    @Override
    public int exportByKeys(java.util.ArrayList<java.util.ArrayList<String>> data, String kindHash) throws Exception
    {
        return 0;
    }

    private void exportB()
    {
        java.io.BufferedWriter writer = null;
        java.util.List<java.util.List<IEditItem>> list = null;
        try
        {
            StringBuilder buf = new StringBuilder();
            for (java.util.List<IEditItem> items : list)
            {
                for (IEditItem item : items)
                {
                    buf.append(item.getType()).append(',').append(item.exportAsTxt(null)).append(';');
                }
                writer.write(buf.append('\n').toString());
                buf.delete(0, buf.length());
            }
        }
        catch (Exception exp)
        {
        }
        finally
        {
            Bean.closeWriter(writer);
        }
    }

    private void importB()
    {
        java.io.BufferedReader reader = null;
        try
        {
            reader = new java.io.BufferedReader(new java.io.FileReader(""));
            String line = reader.readLine();
            String[] arr1;
            String tmp1;
            String tmp2;
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^\\d+");
            java.util.regex.Matcher matcher;
            while (line != null)
            {
                arr1 = line.replace("\\;", "\b").replace("\\,", "\f").split(";");
                if (arr1 == null || arr1.length < ConsEnv.PWDS_HEAD_SIZE)
                {
                    continue;
                }
                for (int i = 0; i < arr1.length; i += 1)
                {
                    tmp1 = arr1[i].trim();
                    matcher = pattern.matcher(tmp1);
                    if (!matcher.find())
                    {
                        continue;
                    }
                    tmp2 = matcher.group();
                    IEditItem item = AEditItem.getInstance(null, Integer.parseInt(tmp2, 10));
                    if (item != null)
                    {
                        item.importByTxt(tmp1.substring(tmp2.length()));
                    }
                }
                line = reader.readLine();
            }
        }
        catch (Exception exp)
        {
        }
        finally
        {
            Bean.closeReader(reader);
        }
    }
}
