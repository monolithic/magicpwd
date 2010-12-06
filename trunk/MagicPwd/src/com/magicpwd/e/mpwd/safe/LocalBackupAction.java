/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;

/**
 * 备份单条记录
 * @author Amon
 */
public class LocalBackupAction extends AMpwdAction
{

    public LocalBackupAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.localBackup("");
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
