/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.skin;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.m.UserCfg;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author aven
 */
public class LookAction extends javax.swing.AbstractAction implements IPwdAction
{

    private CoreMdl coreMdl;

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

        UserCfg cfg = coreMdl.getUserCfg();
        if (ConsCfg.DEF_SKIN_DEF.equals(command))
        {
            type = "java";
            look = ConsCfg.DEF_SKIN_DEF;
            name = ConsCfg.DEF_SKIN_DEF;
        }
        else if (ConsEnv.SKIN_LOOK_SYSTEM.equals(command))
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

        Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30FAA1B, "新外观将在重启后生效！\n注意：一些外观可能造成性能问题，如果您觉得所选择的外观使系统运行变慢，\n　　　请选择使用“默认”或“系统”外观！");
    }

    @Override
    public void setMainPtn(MainPtn mainPtn)
    {
//        this.mainPtn = mainPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    @Override
    public void doUpdate(Object object)
    {
    }
}
