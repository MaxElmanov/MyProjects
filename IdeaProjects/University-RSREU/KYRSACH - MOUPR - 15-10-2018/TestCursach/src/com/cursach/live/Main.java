package com.cursach.live;

public class Main {

    public static void main(String[] args) {

        int mass[][] = {
                {7, 2, 9},
                {2, 9, 0},
                {9, 0, 11}};

        int setA [] = new int [3];
        int setB [] = new int [3];

        int iterator = maxSumInLine(mass);

        setA = getStartSet(mass, iterator, setA);

        for (int el : setA)
            System.out.println(el+"\t");
    }

    private static int[] getStartSet(int [][] mass, int iter, int [] setA) {
        for (int j = 0; j < mass[iter].length; j++) {
            setA[j] = mass[iter][j];
        }
        return setA;
    }

    static int maxSumInLine(int [][] mass){
        int [] sumArr = new int [3];
        int rez = 0;
        int iter = 0;
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i].length; j++) {
                sumArr[i] += mass[i][j];
            }
        }

        rez = sumArr[0];
        for (int i = 0; i < sumArr.length-1; i++) {
            if(rez < sumArr[i+1]){
                rez = sumArr[i+1];
                iter = i+1;
            }
        }
        return iter;
    }
}
