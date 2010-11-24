package test;

import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aven
 */
public class Test
{

    public static void main(String[] args)
    {
        System.out.println(System.nanoTime());
        long s = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        long l = c.getTimeInMillis();
        System.out.println(s + "\n" + l);
        System.out.println(l);
        c.add(Calendar.SECOND, 1);
        System.out.println(c.getTimeInMillis() - l);
    }
}
