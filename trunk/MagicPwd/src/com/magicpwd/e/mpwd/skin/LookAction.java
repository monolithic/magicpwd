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
import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd.m.UserCfg;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author aven
 */
public class LookAction extends APwdAction
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

        UserCfg cfg = coreMdl.getUserCfg();
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
        cfg.setCfg(ConsCfg.CFG_SKIN_TYPE, type);
        cfg.setCfg(ConsCfg.CFG_SKIN_LOOK, look);
        cfg.setCfg(ConsCfg.CFG_SKIN_NAME, name);
        cfg.setCfg(ConsCfg.CFG_SKIN_DECO, deco);

        Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30FAA1B, "新外观将在重启后生效！\n注意：一些外观可能造成性能问题，如果您觉得所选择的外观使系统运行变慢，\n　　　请选择使用“默认”或“系统”外观！");
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(Object object)
    {
    }
}
