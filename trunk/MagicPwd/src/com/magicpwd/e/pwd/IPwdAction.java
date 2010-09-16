/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd;

import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author aven
 */
public interface IPwdAction
{

    void setMainPtn(MainPtn mainPtn);

    void setCoreMdl(CoreMdl coreMdl);

    void doUpdate(Object object);
}
