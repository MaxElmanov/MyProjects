package ru.games.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.games.bean.Genofond;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogicGeneration {

    public static final Logger log = LogManager.getLogger(LogicGeneration.class);

    private List<Double> results = new ArrayList<>();
    private Genofond genofond;

    public List<Double> generateGeneration(Genofond genofond){

        results.clear();

        this.genofond = genofond;

        genofond.setListsOfXY(genofond);

        for(int i = 0; i < genofond.getListX().size(); i++){
            for(int j = 0; j < genofond.getListY().size(); j++){
                if(i == j) {
                    results.add(function(genofond.getListX().get(i), genofond.getListY().get(j)));
                }
            }
        }

        newGeneration();

        return results;
    }

    private Double function(Double x, Double y){
        return x / (x*x + 2*y*y +1);
    }

    private void newGeneration(){

        int minIndex = 0;
        int maxIndex = 0;

        Double minVal = results.get(0);
        Double maxVal = results.get(0);
        for (int i = 0; i < results.size(); i++){
            if (results.get(i) < minVal){
                minVal = results.get(i);
                minIndex = i;
            }
            if (results.get(i) > maxVal){
                maxVal = results.get(i);
                maxIndex = i;
            }
        }

        List<Double> listBestX = new ArrayList<>();
        List<Double> listBestY = new ArrayList<>();

        for (int i = 0; i < genofond.getListX().size(); i++){
            for (int j = 0; j < genofond.getListY().size(); j++){
                if (i == j && i != minIndex && j != minIndex){
                    listBestX.add(genofond.getListX().get(i));
                    listBestY.add(genofond.getListY().get(j));
                }
            }
        }

        formNewGeneration(maxIndex, listBestX, listBestY);
    }

    private void formNewGeneration(int maxIndex, List<Double> listBestX, List<Double> listBestY) {

        //for the best
        Double maxX = genofond.getListX().get(maxIndex);
        Double maxY = genofond.getListY().get(maxIndex);

        genofond.setX3(maxX);
        genofond.setX4(maxX);

        genofond.setY1(maxY);
        genofond.setY2(maxY);

        //for other
        int[] indexesWithoutMaxIndex = new int[3];
        int k = 0;
        for(int i = 0; i < listBestX.size(); i++){
            for(int j = 0; j < listBestY.size(); j++){
                if(i == j && i != maxIndex && j != maxIndex) {
                    indexesWithoutMaxIndex[k++] = i; //whatever i or j, because i == j condition in if
                }
            }
        }

        genofond.setX1(listBestX.get(indexesWithoutMaxIndex[0]));
        genofond.setX2(listBestX.get(indexesWithoutMaxIndex[1]));

        genofond.setY3(listBestY.get(indexesWithoutMaxIndex[0]));
        genofond.setY4(listBestY.get(indexesWithoutMaxIndex[1]));

        //for mutation
        Double mutationValue = getMutationValue();
        int mutationIndex = getMutationIndex();

//        log.info("mutationValue " + mutationValue);
//        log.info("mutationIndex " + mutationIndex);

        for(int i = 0; i < genofond.getListX().size(); i++){
            for(int j = 0; j < genofond.getListY().size(); j++){
                switch(mutationIndex){
//                    X
                    case 1:
                        genofond.setX1(mutationValue);
                        break;
                    case 2:
                        genofond.setX2(mutationValue);
                        break;
                    case 3:
                        genofond.setX3(mutationValue);
                        break;
                    case 4:
                        genofond.setX4(mutationValue);
                        break;
//                    Y
                    case 5:
                        genofond.setY1(mutationValue);
                        break;
                    case 6:
                        genofond.setY2(mutationValue);
                        break;
                    case 7:
                        genofond.setY3(mutationValue);
                        break;
                    case 8:
                        genofond.setY4(mutationValue);
                        break;
                }
            }
        }

        //set listX and listY
        genofond.setListsOfXY(genofond);
    }

    private Double getMutationValue() {
        int max = 10;
        int min = 0;

        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    private int getMutationIndex() {

        int max = 8;
        int min = 1;

        return (int)Math.floor(Math.random() * (max - min + 1)) + min;
    }

}
