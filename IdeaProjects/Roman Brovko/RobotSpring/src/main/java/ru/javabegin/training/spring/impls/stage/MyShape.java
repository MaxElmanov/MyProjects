package ru.javabegin.training.spring.impls.stage;

import org.springframework.beans.factory.annotation.Qualifier;

public class MyShape
{
    private ShapeEnum shape;

    public ShapeEnum getShape()
    {
        return shape;
    }

    public void setShape(ShapeEnum shape)
    {
        this.shape = shape;
    }
}
