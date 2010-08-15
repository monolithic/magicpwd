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
import com.magicpwd.v.TrayPtn;
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
        final String name;

        if (ConsEnv.SKIN_DEFAULT.equals(command))
        {
            look = ConsEnv.SKIN_DEFAULT;
            UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_NAME, ConsEnv.SKIN_DEFAULT);
            name = javax.swing.UIManager.getCrossPlatformLookAndFeelClassName();
        }
        else if (ConsEnv.SKIN_SYSTEM.equals(command))
        {
            look = ConsEnv.SKIN_SYSTEM;
            UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_NAME, ConsEnv.SKIN_SYSTEM);
            name = javax.swing.UIManager.getSystemLookAndFeelClassName();
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

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                TrayPtn.changeSkin(name);
            }
        });
        Lang.showMesg(null, LangRes.P30FAA1B, "系统不能保证风格切换正常，请重新启动程序以使用新的界面风格！");
    }
}
