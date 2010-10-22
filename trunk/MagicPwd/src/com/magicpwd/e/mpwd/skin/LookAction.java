/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.skin;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.v.TrayPtn;

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

        Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30FAA1B, "新外观将在重启后生效！\n注意：一些外观可能造成性能问题，如果您觉得所选择的外观使系统运行变慢，\n　　　请选择使用“默认”或“系统”外观！");
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
