package arraycopy;

public class CommonTypeArray
{
    public static void main(String[] args)
    {
        int[] arr1 = {1, 2, 2, 3, 4};
        int[] arr2 = new int[arr1.length];

        System.arraycopy(arr1, 0, arr2, 0, arr1.length);

        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }

        System.out.println();

        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]);
        }
    }
}
