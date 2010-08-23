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

        String look;
        String name;

        if (ConsEnv.SKIN_DEFAULT.equals(command))
        {
            look = ConsEnv.SKIN_DEFAULT;
            name = ConsEnv.SKIN_DEFAULT;
        }
        else if (ConsEnv.SKIN_SYSTEM.equals(command))
        {
            look = ConsEnv.SKIN_SYSTEM;
            name = ConsEnv.SKIN_SYSTEM;
        }
        else
        {
            String[] arr = command.split(",");
            if (arr == null || arr.length != 2)
            {
                return;
            }
            look = arr[0];
            name = arr[1];
            UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_NAME, name);
        }
        UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_LOOK, look);
        UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_NAME, name);

        Lang.showMesg(null, LangRes.P30FAA1B, "新外观将在重启后生效！\n注意：一些外观可能造成性能问题，如果您觉得所选择的外观使系统运行变慢，\n　　　请选择使用“默认”或“系统”外观！");
    }
}
