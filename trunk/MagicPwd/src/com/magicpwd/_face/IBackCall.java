/**
 * 
 */
package com.magicpwd._face;

import java.util.EventListener;

/**
 * @author amon
 * 
 */
public interface IBackCall
{
    boolean callBack(Object sender, EventListener event, String... params);
}
