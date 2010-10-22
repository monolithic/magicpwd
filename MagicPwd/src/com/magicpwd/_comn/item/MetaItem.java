/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn.item;

import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Amon
 */
public class MetaItem extends EditItem
{

    public MetaItem(UserMdl userMdl)
    {
        super(userMdl, ConsDat.INDX_META);
    }
}
