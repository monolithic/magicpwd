/**
 * 
 */
package com.magicpwd.__i;

import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 */
public interface IGridView
{

    UserMdl getCoreMdl();

    void selectNext(int step, boolean updt);
}
