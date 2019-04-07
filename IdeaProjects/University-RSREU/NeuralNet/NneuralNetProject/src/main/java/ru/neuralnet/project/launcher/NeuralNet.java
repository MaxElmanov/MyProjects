package ru.neuralnet.project.launcher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeuralNet {

    //input parameters
    private int alcohol = 1;
    private int rainy = 1;
    private int bestFriend = 1;
    private float learningRate = 0.1f;

    private double[][] weights_0_1;
    private double[][] weights_1_2;
    private double[] input_neurons;

    public NeuralNet(int alcohol, int rainy, int bestFriend, float learningRate) {
        this.alcohol = alcohol;
        this.rainy = rainy;
        this.bestFriend = bestFriend;
        this.learningRate = learningRate;

        input_neurons = new double[]{alcohol, rainy, bestFriend};

//        weights_0_1 = random(2, 3);
//        weights_1_2 = random(1, 2);

        weights_0_1 = new double[][]{{0.79, 0.44, 0.43}, {0.85, 0.43, 0.29}};
        weights_1_2 = new double[][]{{0.5, 0.52}};

        showMatrix("input neurons", input_neurons);
        showMatrix("Weights between 0 and 1 levels", weights_0_1);
        showMatrix("Weights between 1 and 2 levels", weights_1_2);
    }

    public static void showMatrix(String name, double[][] mas) {
        System.out.println(name);

        for (int i = 0; i < mas.length; i++) {
            for (int j = 0; j < mas[i].length; j++) {
                System.out.print(mas[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void showMatrix(String name, double[] mas) {
        System.out.println(name);

        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + "\t");
        }
        System.out.println();
    }

    public double[][] random(int rows, int columns) {

        double[][] temp = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = Math.random();
            }
        }

        return temp;
    }

    public double[] times(double[] neurons, double[][] weights) {

        //кол-во массивов с весами(weights) равно кол-ву нейронов(resultNeurons) в результирующем массиве (internal neurons)
        double[] resultNeurons = new double[weights.length];

        //кол-во нейронов в результирующем массиве
        for (int i = 0; i < resultNeurons.length; i++) {

            float sum_neurons_x_weights = 0.0f;

            for (int j = 0; j < neurons.length; j++) {
                sum_neurons_x_weights += neurons[j] * weights[i][j];
            }

            resultNeurons[i] = sum_neurons_x_weights;
        }

        //результирующий массив содержит суммы для каждого из внутренних нейронов
        return resultNeurons;
    }

    public double[] sigmoid(double[] mas) {

        double[] neuronsSigmoided = new double[mas.length];

        for (int j = 0; j < neuronsSigmoided.length; j++) {
            neuronsSigmoided[j] = 1 / (1 + Math.pow(Math.E, -mas[j]));
        }

        return neuronsSigmoided;
    }

    public double predict(double[] inputs) {
        double[] inputs_1 = null;

        if (inputs.length == 0) {
            inputs_1 = times(input_neurons, weights_0_1);
        } else {
            inputs_1 = times(inputs, weights_0_1);
        }

        double[] output_1 = sigmoid(inputs_1);
//        showMatrix("output_1", output_1);

        double[] input_2 = times(output_1, weights_1_2);
        double[] output_2 = sigmoid(input_2);
//        showMatrix("output_2", output_2);

        return output_2[0];
    }

    public boolean train(double[] inputs, double expected) {

        //LAYER 1_2
        double actual_predict = predict(inputs);

        double error_layer_2 = actual_predict - expected;
        double sigmoidDx_layer_2 = actual_predict * (1 - actual_predict);
        double weightDelta_layer_2 = error_layer_2 * sigmoidDx_layer_2;

        double[][] new_weight_1_2 = weights_1_2;
        //кол-во элементов в массиве весов между внутренним и выходным слоями (1_2)
        for (int i = 0; i < weights_1_2[0].length; i++) {
            new_weight_1_2[0][i] -= actual_predict * weightDelta_layer_2 * learningRate;
        }
        weights_1_2 = new_weight_1_2;

        //LAYER 0_1
        for (int i = 0; i < weights_1_2[0].length; i++) {
            double error_layer_1 = weights_1_2[0][i] * weightDelta_layer_2;
            double sigmoidDx_layer_1 = sigmoidDx_layer_2;
            double weightDelta_layer_1 = error_layer_1 * sigmoidDx_layer_1;

            double[][] new_weight_0_1 = weights_0_1;
            //кол-во элементов в массиве весов между внутренним и выходным слоями (0_1)
            for (int j = 0; j < weights_0_1[i].length; j++) {
                new_weight_0_1[i][j] -= actual_predict * weightDelta_layer_1 * learningRate;
            }
            weights_0_1 = new_weight_0_1;
        }

        return countResult(actual_predict);
    }

    public boolean countResult(double x) {
        return x >= 0.5 ? true : false;
    } //??????????????????? false <-> true

    public double MSE(double a, double b) {
        return Math.pow(a - b, 2);
    }

    public double smartFloor(double x){
        return x >= 0.5 ? 1 : 0;
    }

}
