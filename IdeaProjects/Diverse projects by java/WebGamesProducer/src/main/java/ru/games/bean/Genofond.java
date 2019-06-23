package ru.games.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genofond {

    private Double
            x1 = -2.0,
            x2 = -1.0,
            x3 =  0.0,
            x4 =  2.0;
    private Double
            y1 =  0.0,
            y2 = -2.0,
            y3 = -1.0,
            y4 =  1.0;

    private List<Double> listX = new ArrayList<>();
    private List<Double> listY = new ArrayList<>();

    public void setListsOfXY(Genofond genofond){
        listX.add(genofond.getX1());
        listX.add(genofond.getX2());
        listX.add(genofond.getX3());
        listX.add(genofond.getX4());

        listY.add(genofond.getY1());
        listY.add(genofond.getY2());
        listY.add(genofond.getY3());
        listY.add(genofond.getY4());
    }

}
