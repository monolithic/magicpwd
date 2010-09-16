/**
 * 
 */
package com.magicpwd._face;

import com.magicpwd.m.CoreMdl;

/**
 * @author Amon
 * 
 */
public interface IGridView
{

    CoreMdl getCoreMdl();

    void selectNext(int step, boolean updt);
}
