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
import com.magicpwd.m.UserMdl;
import javax.swing.AbstractAction;

/**
 *
 * @author aven
 */
public class LookAction extends AbstractAction
{

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
            UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_NAME, name);
        }
        UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_TYPE, type);
        UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_LOOK, look);
        UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_NAME, name);

        Lang.showMesg(null, LangRes.P30FAA1B, "新外观将在重启后生效！\n注意：一些外观可能造成性能问题，如果您觉得所选择的外观使系统运行变慢，\n　　　请选择使用“默认”或“系统”外观！");
    }
}
