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
package com.magicpwd.e.mpwd.skin;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.__a.mpwd.AMpwdAction;

/**
 *
 * @author aven
 */
public class LookAction extends AMpwdAction
{

    public LookAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if (!Char.isValidate(command))
        {
            return;
        }

        String type;
        String look;
        String name;
        String deco;

        if (ConsCfg.DEF_SKIN_DEF.equals(command))
        {
            type = "java";
            look = ConsCfg.DEF_SKIN_DEF;
            name = ConsCfg.DEF_SKIN_DEF;
            deco = "false";
        }
        else if (ConsEnv.SKIN_LOOK_SYSTEM.equals(command))
        {
            type = "java";
            look = ConsCfg.DEF_SKIN_SYS;
            name = ConsCfg.DEF_SKIN_SYS;
            deco = "true";
        }
        else
        {
            String[] arr = command.split("[:,]");
            if (arr == null || arr.length != 4)
            {
                return;
            }
            type = arr[0];
            look = arr[1];
            name = arr[2];
            deco = arr[3];
        }
        mainPtn.getUserMdl().setCfg(ConsCfg.CFG_SKIN_TYPE, type);
        mainPtn.getUserMdl().setCfg(ConsCfg.CFG_SKIN_LOOK, look);
        mainPtn.getUserMdl().setCfg(ConsCfg.CFG_SKIN_NAME, name);
        mainPtn.getUserMdl().setCfg(ConsCfg.CFG_SKIN_DECO, deco);

        Lang.showMesg(mainPtn, LangRes.P30FAA1B, "新外观将在重启后生效！\n注意：一些外观可能造成性能问题，如果您觉得所选择的外观使系统运行变慢，\n　　　请选择使用“默认”或“系统”外观！");
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }
}
