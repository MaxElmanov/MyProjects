package CourseWorkMOUPR;

public class Matrix {

    private int[][] arr;
    private int detA;
    private int[][] At;
    private int[][] Av;
    private double [][] Aback;
    private double[][] matrixE;

    public Matrix(int[][] arr) {
        this.arr = arr;
    }

    public int getdetA(){
        detA = arr[0][0]*arr[1][1]*arr[2][2] + arr[1][0]*arr[2][1]*arr[0][2] + arr[0][1]*arr[1][2]*arr[2][0] - arr[2][0]*arr[1][1]*arr[0][2] - arr[1][0]*arr[0][1]*arr[2][2] - arr[0][0]*arr[2][1]*arr[1][2];
        //detA = 0;
        if(detA == 0) {
            System.out.println("Error 123: detA = 0");
            System.exit(0);
        }

        return detA;
    }

    public int[][] getAt(){

        At = new int[3][3];
        arrCopyIntoAt();

        boolean[][] wasChanged = new boolean[][]{{false,false,false}, {false,false,false}, {false,false,false}};

        for (int i = 0; i < At.length; i++) {
            for (int j = 0; j < At[i].length; j++) {
                if(!wasChanged[i][j]) {
                    int temp = At[i][j];
                    At[i][j] = At[j][i];
                    At[j][i] = temp;
                    wasChanged[i][j] = wasChanged[j][i] = true;
                }
            }
        }
        return At;
    }
    private void arrCopyIntoAt() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                At[i][j] = arr[i][j];
            }
        }
    }

    public void printInitialArray(){
        System.out.println("Начальный массив");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (arr[i][j] >= 0) {
                    System.out.print("  " + arr[i][j]);
                } else {
                    System.out.print(" " + arr[i][j]);
                }
            }
            System.out.println();
        }
    }

    public int[][] getAdditionsMatrix(){

        Av = new int[3][3];
        int[][]cubesArr = new int[9][4];
        int row = 0;
        int col = 0;

        //3
        for (int x = 0; x < cubesArr.length; x++) {
            cubesArr[x] = getOneAddition(row, col, x);

            if(col == 2) {
                row++;
                col = 0;
            }
            else{
                col++;
            }
        }

        //4
        int index = 0; //index massive of four numbers (from 0 - to 8)
        for (int i = 0; i < Av.length; i++) {
            for (int j = 0; j < Av[i].length; j++) {
                Av[i][j] = (int)Math.pow(-1, (i+j) ) * getValueOneAddition(index, cubesArr);
                index++;
            }
        }

        return Av;
    }
    private int[] getOneAddition(int row, int col, int x) {

        int[] tempCubeArr = new int[4];

        int t = 0;
        for (int y = 0; y < At.length; y++) {
            for (int z = 0; z < At[y].length; z++) {
                if(y != row && z != col) {
                    tempCubeArr[t] = At[y][z];
                    t++;
                }
            }
        }

        return tempCubeArr;
    }
    private int getValueOneAddition(int index, int[][]cubesArr) {
        return cubesArr[index][0] * cubesArr[index][3] - cubesArr[index][1] * cubesArr[index][2];
    }

    public double[][] getBackwardMatrix() {
        Aback = new double[3][3];

        for (int i = 0; i < Av.length; i++) {
            for (int j = 0; j < Av[i].length; j++) {
                Aback[i][j] = (double)1/detA * Av[i][j];
            }
        }

        return Aback;
    }

    public double[][] getE(){
        matrixE = new double[3][3];
        //boolean[][] wasChanged = new boolean[][]{{false,false,false}, {false,false,false}, {false,false,false}};
        int row = 0;
        int col = 0;

        for (int x = 0; x < matrixE.length*matrixE.length; x++) {
            if (row == 0 && col == 0) {
                matrixE[row][col] += (Av[0][0]*arr[0][0] + Av[0][1]*arr[1][0] + Av[0][2]*arr[2][0]);
            }
            else if (row == 0 && col == 1) {
                matrixE[row][col] += (Av[0][0]*arr[0][1] + Av[0][1]*arr[1][1] + Av[0][2]*arr[2][1]);
            }
            else if (row == 0 && col == 2) {
                matrixE[row][col] += (Av[0][0]*arr[0][2] + Av[0][1]*arr[1][2] + Av[0][2]*arr[2][2]);
            }
            //
            else if (row == 1 && col == 0) {
                matrixE[row][col] += (Av[1][0]*arr[0][0] + Av[1][1]*arr[1][0] + Av[1][2]*arr[2][0]);
            }
            else if (row == 1 && col == 1) {
                matrixE[row][col] += (Av[1][0]*arr[0][1] + Av[1][1]*arr[1][1] + Av[1][2]*arr[2][1]);
            }
            else if (row == 1 && col == 2) {
                matrixE[row][col] += (Av[1][0]*arr[0][2] + Av[1][1]*arr[1][2] + Av[1][2]*arr[2][2]);
            }
            //
            else if (row == 2 && col == 0) {
                matrixE[row][col] += (Av[2][0]*arr[0][0] + Av[2][1]*arr[1][0] + Av[2][2]*arr[2][0]);
            }
            else if (row == 2 && col == 1) {
                matrixE[row][col] += (Av[2][0]*arr[0][1] + Av[2][1]*arr[1][1] + Av[2][2]*arr[2][1]);
            }
            else if (row == 2 && col == 2) {
                matrixE[row][col] += (Av[2][0]*arr[0][2] + Av[2][1]*arr[1][2] + Av[2][2]*arr[2][2]);
            }

            //when all were added, we can divide by detA
            matrixE[row][col] /= detA;

            if(col == 2) {
                row++;
                col = 0;
            }
            else{
                col++;
            }
        }

        return matrixE;
    }
}
