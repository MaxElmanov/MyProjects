package binaryTree;

import org.junit.*;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeTest
{
    private static BinaryTree binaryTree;

    @BeforeClass
    public static void classStart()
    {
        binaryTree = new BinaryTree();
    }

    @Before
    public void start()
    {
        System.out.println("start");
        binaryTree.printEntire();
    }

    public static Integer[] params = {3, 7, 8, 3, 15, 13, 17, 5, 10, 6, 8};

    public static Integer[][] data = {
            {7, 8, 3}, //0
            {7, 8, 3}, //1
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18}, //2
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18, 4}, //3
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18}, //4
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18}, //5
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18}, //6
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18}, //7
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18}, //8
            {10, 3, 1, 5, 0, 2, 6, 15, 13, 17, 16, 18}, //9
            {12, 8, 16, 13, 18, 3, 10, 9, 1, 5, 4, 6, 7}}; //10

    @Test
    public void remove()
    {
        int index = 10;
        Integer[] partData = data[index];
        Integer removedParam = params[index];

        System.out.println("part DATA");
        for (int i = 0; i < partData.length; i++) {
            binaryTree.add(partData[i], "a");
            System.out.print(partData[i] + "\t");
        }
        System.out.println();

        int oldLength = binaryTree.getAmountElements();
        System.out.println("removed param " + removedParam);
        System.out.println("oldLength " + oldLength);

        binaryTree.remove(removedParam);

        int newLength = binaryTree.getAmountElements();
        System.out.println("newLength " + newLength);
        Assert.assertNotEquals(oldLength, newLength);
    }

    @After
    public void classStop()
    {
        binaryTree.printEntire();
        binaryTree.clear();
        System.out.println("stop\n");
    }

    //    @DataPoints
////    public static Integer[][] params = {
////            {3},
////            {3, 7,8,3},
////            {8, 7,8,3},
////            {3,  10,3,1,5,0,2,6,15,13,17,16,18}, //? size=12 of each
////            {15, 10,3,1,5,0,2,6,15,13,17,16,18},
////            {13, 10,3,1,5,0,2,6,15,13,17,16,18},
////            {1,  10,3,1,5,0,2,6,15,13,17,16,18},
////            {17, 10,3,1,5,0,2,6,15,13,17,16,18},
////            {5,  10,3,1,5,0,2,6,15,13,17,16,18},
////            {10, 10,3,1,5,0,2,6,15,13,17,16,18},
////            {6,  10,3,1,5,0,2,6,15,13,17,16,18}};
}