/**
 * 
 */
package com.magicpwd.$i;

import java.util.EventListener;

/**
 * @author Amon
 */
public interface IBackCall
{

    boolean callBack(Object sender, EventListener event, String... params);
}
