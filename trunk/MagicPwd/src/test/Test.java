/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

public class Test
{

    public static void main(String[] args)
    {
        System.out.println("11+2 　1%2+%%%  3".replaceFirst("^[+\\s]*", "%").replaceFirst("[+\\s]*$", "%").replaceAll("[+%\\s]+", "%"));
    }
}
