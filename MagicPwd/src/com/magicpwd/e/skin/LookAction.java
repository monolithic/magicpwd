/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.skin;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.m.UserCfg;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author aven
 */
public class LookAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private UserMdl coreMdl;

    public LookAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
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

        UserCfg cfg = coreMdl.getUserCfg();
        if (ConsCfg.DEF_SKIN_DEF.equals(command))
        {
            type = "java";
            look = ConsCfg.DEF_SKIN_DEF;
            name = ConsCfg.DEF_SKIN_DEF;
        }
        else if (ConsEnv.SKIN_SYSTEM.equals(command))
        {
            type = "java";
            look = ConsCfg.DEF_SKIN_SYS;
            name = ConsCfg.DEF_SKIN_SYS;
        }
        else
        {
            String[] arr = command.split("[:,]");
            if (arr == null || arr.length != 3)
            {
                return;
            }
            type = arr[0];
            look = arr[1];
            name = arr[2];
            cfg.setCfg(ConsCfg.CFG_SKIN_NAME, name);
        }
        cfg.setCfg(ConsCfg.CFG_SKIN_TYPE, type);
        cfg.setCfg(ConsCfg.CFG_SKIN_LOOK, look);
        cfg.setCfg(ConsCfg.CFG_SKIN_NAME, name);

        Lang.showMesg(mainPtn, LangRes.P30FAA1B, "新外观将在重启后生效！\n注意：一些外观可能造成性能问题，如果您觉得所选择的外观使系统运行变慢，\n　　　请选择使用“默认”或“系统”外观！");
    }
}
