/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__i.mpwd;

import com.magicpwd.__i.IAction;
import com.magicpwd.v.mpwd.MainPtn;

/**
 *
 * @author aven
 */
public interface IPwdAction extends IAction
{

    void setMainPtn(MainPtn mainPtn);
}
