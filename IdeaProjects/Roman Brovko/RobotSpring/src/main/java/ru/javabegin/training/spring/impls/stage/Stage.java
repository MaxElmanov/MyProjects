package ru.javabegin.training.spring.impls.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Objects;

public class Stage
{
    private int width;
    private int height;

    @Autowired(required = false)
    @Qualifier("shapeRectangle")
    private MyShape shape;

    private Stage(){}

    private static class Inner{
        private static Stage stage;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public MyShape getShape()
    {
        return shape;
    }

    public void setShape(MyShape shape)
    {
        this.shape = shape;
    }

    public static Stage getInstance(){
        if (Objects.isNull(Inner.stage)){
            Inner.stage = new Stage();
        }
        return Inner.stage;
    }
}
