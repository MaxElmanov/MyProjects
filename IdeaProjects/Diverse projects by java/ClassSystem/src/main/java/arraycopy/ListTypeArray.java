package arraycopy;

import java.util.ArrayList;
import java.util.List;

/**
 * This sample represents an idea that List<?WrapperType> can not be used with System.arraycopy() method
 * @author Maxim Elmanov
 */

public class ListTypeArray
{
    public static void main(String[] args)
    {
        List<Integer> arrOne = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
        List<Integer> arrTwo = new ArrayList<Integer>();

        System.arraycopy(arrOne, 0, arrTwo, 0, arrOne.size());

        for (int i = 0; i < arrOne.size(); i++) {
            System.out.print(arrOne.get(i));
        }

        System.out.println();

        for (int i = 0; i < arrTwo.size(); i++) {
            System.out.print(arrTwo.get(i));
        }
    }
}
