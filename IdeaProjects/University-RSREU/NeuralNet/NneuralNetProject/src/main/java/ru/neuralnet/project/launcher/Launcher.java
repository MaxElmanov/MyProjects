package ru.neuralnet.project.launcher;

public class Launcher {

    static int epochs = 1000;
    static float learning_rate = 0.05f;

    static double[][][] trainArr = {
            {{0, 0, 0}, {0}},
            {{0, 0, 1}, {1}},
            {{0, 1, 0}, {0}},
            {{0, 1, 1}, {0}},
            {{1, 0, 0}, {1}},
            {{1, 0, 1}, {1}},
            {{1, 1, 0}, {0}},
            {{1, 1, 1}, {1}}
    };

    public static void main(String[] args) {

        NeuralNet obj = new NeuralNet(1, 1, 0, learning_rate);

        //Usage before education
        System.out.println(obj.countResult(obj.predict(new double[]{0, 0, 1})));

        //Education
        for (int i = 0; i < epochs; i++) {
            showNumberEpoch(i);

            for (int j = 0; j < trainArr.length; j++) {
                boolean answer_NN = obj.train(trainArr[j][0], trainArr[j][1][0]);
                if (i <= 5 || i >= 990) {
                    System.out.printf("distinction = %s", obj.MSE(obj.predict(trainArr[j][0]), trainArr[j][1][0]));
                    showExpectedAnswer(trainArr[j][1][0]);
                    System.out.print(" | received = " + answer_NN);
                    showMyArray(trainArr[j][0], trainArr[j][1][0]);
                    convenientOutput(obj.smartFloor(obj.predict(trainArr[j][0])), trainArr[j][1][0]);

//                obj.showMatrix("Weights_0_1", obj.getWeights_0_1());
//                obj.showMatrix("Weights_1_2", obj.getWeights_1_2());
                }
            }

            smartNewLine(i);
        }

        //Usage after education
        System.out.println(obj.countResult(obj.predict(new double[]{0, 0, 1})));

    }

    private static void showNumberEpoch(int i) {
        if (i <= 5 || i >= 990) {
            System.out.println("№ " + i);
        }
    }

    private static void convenientOutput(double got, double expected) {
        if(got == expected) {
            System.out.print(" | Y\n");
        }
        else{
            System.out.print(" | N\n");
        }
    }

    private static void smartNewLine(int i) {
        if (i <= 5 || i >= 990) {
            System.out.println();
        }
    }

    private static void showMyArray(double[] trainArr, double expected) {
        System.out.printf(" | array: {{%.0f, %.0f, %.0f}, {%.0f}} ", trainArr[0], trainArr[1], trainArr[2], expected);
    }

    private static void showExpectedAnswer(double expected) {
        if (expected == 1) {
            System.out.print(" | expected = " + true);
        } else if (expected == 0) {
            System.out.print(" | expected = " + false);
        } else {
            try {
                throw new Exception("check expected values");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
