/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__i;

/**
 *
 * @author Amon
 */
public interface IFavIcon
{

    javax.swing.Icon getIcon(String hash);

    void setIcon(String hash, javax.swing.Icon icon);
}
