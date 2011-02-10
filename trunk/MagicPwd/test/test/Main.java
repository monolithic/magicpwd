package test;

import java.util.regex.Pattern;

public class Main
{

    public static void main(String args[])
    {
        System.out.println(Pattern.compile(".+\\.jar$").matcher("JTattoo-1.4.2.jar").matches());
    }
}
