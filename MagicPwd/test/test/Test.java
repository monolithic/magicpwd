package test;


import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aven
 */
public class Test {
    public static void main(String[] args)
    {
        System.out.println(Pattern.matches("^(?!default).+$", "default"));
    }
}
