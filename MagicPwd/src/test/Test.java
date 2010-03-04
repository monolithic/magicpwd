/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.regex.Pattern;

public class Test
{

    public static void main(String[] args)
    {
        System.out.println(Pattern.matches("^amon_[^.]+\\.backup$", "amon_20100228-184007.backup"));
    }
}
