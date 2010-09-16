/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn.item;

import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserCfg;

/**
 *
 * @author Amon
 */
public class MetaItem extends EditItem
{

    public MetaItem(UserCfg userCfg)
    {
        super(userCfg, ConsDat.INDX_META);
    }
}
