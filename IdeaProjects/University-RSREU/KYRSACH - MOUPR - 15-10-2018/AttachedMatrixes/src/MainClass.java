import CourseWorkMOUPR.Matrix;

public class MainClass {
    public static void main(String[] args) {

        //initial matrix
        int[][] array = new int[][]{{1, 2, -1}, {3, 0, 2}, {4, -2, 5}};
        int[][] tempIntArr;
        double[][] tempDoubleArr;

        //1
        Matrix matrix = new Matrix(array);
        System.out.println("detA = " + matrix.getdetA());

        System.out.println();
        matrix.printInitialArray();

        //2
        System.out.println();
        System.out.println(" Транспорированная матрица A^{T}");
        tempIntArr = matrix.getAt();
        for (int i = 0; i < tempIntArr.length; i++) {
            for (int j = 0; j < tempIntArr[i].length; j++) {
                if (tempIntArr[i][j] >= 0) {
                    System.out.print(" " + tempIntArr[i][j] + " ");
                } else {
                    System.out.print(tempIntArr[i][j] + " ");
                }
            }
            System.out.println();
        }

        //3 - 4
        System.out.println();
        System.out.println("Присоединенная матрица A^{V}");
        tempIntArr = matrix.getAdditionsMatrix();
        for (int i = 0; i < tempIntArr.length; i++) {
            for (int j = 0; j < tempIntArr[i].length; j++) {

                if (tempIntArr[i][j] >= 0) {
                    System.out.print(" " + tempIntArr[i][j] + " ");
                } else {
                    System.out.print(tempIntArr[i][j] + " ");
                }
            }
            System.out.println();
        }

        //5
        System.out.println();
        System.out.println("Обратная матрица A^-1");
        tempDoubleArr = matrix.getBackwardMatrix();
        for (int i = 0; i < tempDoubleArr.length; i++) {
            for (int j = 0; j < tempDoubleArr[i].length; j++) {
                if (tempDoubleArr[i][j] >= 0) {
                    System.out.print("    " + tempDoubleArr[i][j]);
                } else {
                    System.out.print("   " + tempDoubleArr[i][j]);
                }
            }
            System.out.println();
        }

        //6 - check  AA^-1 = A^-1A = E
        System.out.println();
        System.out.println("Проверка");
        System.out.println("Единичная матрица");
        tempDoubleArr = matrix.getE();
        for (int i = 0; i < tempDoubleArr.length; i++) {
            for (int j = 0; j < tempDoubleArr[i].length; j++) {

                if (tempDoubleArr[i][j] > 0) {
                    System.out.print("    " + tempDoubleArr[i][j]);
                } else {
                    System.out.print("   " + tempDoubleArr[i][j]);
                }
            }
            System.out.println();
        }
    }
}

