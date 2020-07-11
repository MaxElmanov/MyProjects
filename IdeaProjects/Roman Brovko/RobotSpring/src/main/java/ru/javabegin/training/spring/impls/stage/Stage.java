package ru.javabegin.training.spring.impls;

import java.util.Objects;

public class Stage
{
    private int width;
    private int height;
    private String form;

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

    public String getForm()
    {
        return form;
    }

    public void setForm(String form)
    {
        this.form = form;
    }

    public static Stage getInstance(){
        if (Objects.isNull(Inner.stage)){
            Inner.stage = new Stage();
        }
        return Inner.stage;
    }
}
