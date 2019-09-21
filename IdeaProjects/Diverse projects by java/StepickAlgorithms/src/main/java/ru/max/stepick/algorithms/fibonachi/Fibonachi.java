package ru.max.stepick.algorithms.fibonachi;

import java.math.BigInteger;
import java.util.function.Function;

public class Fibonachi
{

    private BigInteger fibonachi(int n)
    {

        BigInteger n0 = BigInteger.ZERO;
        BigInteger n1 = BigInteger.ONE;

        for(int i = 2; i <= n; i++) {
            BigInteger n0_temp = n0;
            n0 = n1;
            n1 = n1.add(n0_temp);
        }

        return n1;
    }

    private void run(Function<Integer, BigInteger> function, int num)
    {
        long start = System.currentTimeMillis();
        System.out.println(function.apply(num));
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }

    public static void main(String[] args)
    {
        new Fibonachi().run((num) -> new Fibonachi().fibonachi(num), 100000);
    }
}
