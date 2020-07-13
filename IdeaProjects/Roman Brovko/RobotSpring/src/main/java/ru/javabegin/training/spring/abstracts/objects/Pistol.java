package ru.javabegin.training.spring.abstracts.objects;

import ru.javabegin.training.spring.interfaces.Gun;

public class Pistol implements Gun
{
    @Override
    public void shoot()
    {
        System.out.println("Pistol----->>>>>>");
    }
}
