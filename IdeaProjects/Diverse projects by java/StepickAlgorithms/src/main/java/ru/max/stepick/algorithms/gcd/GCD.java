package ru.max.stepick.algorithms.gcd;

import java.math.BigInteger;

/**
 * The class GCD was created to get the Greatest Common Divider for two normal numbers (a, b)
 */

public class GCD
{
    private BigInteger gcd(BigInteger a, BigInteger b)
    {
        while(true) {
            System.out.println(a + " : " + b);
            if(a.equals(BigInteger.ZERO)) return b;
            if(b.equals(BigInteger.ZERO)) return a;

            if(a.compareTo(b) >= 0){
                a = a.mod(b);
            } else{
                b = b.mod(a);
            }
        }
    }

    private void run( )
    {
        BigInteger a = new BigInteger("12323434554656567576786");
        BigInteger b = new BigInteger("9898978978989089076786");
        System.out.println(gcd(a, b));
    }

    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        new GCD().run();
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }
}
