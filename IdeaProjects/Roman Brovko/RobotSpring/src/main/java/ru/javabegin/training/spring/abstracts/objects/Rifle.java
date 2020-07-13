package ru.javabegin.training.spring.abstracts.objects;

import ru.javabegin.training.spring.interfaces.Gun;

public class Rifle implements Gun
{
    @Override
    public void shoot()
    {
        System.out.println("Rifle---->>>>>");
    }
}
