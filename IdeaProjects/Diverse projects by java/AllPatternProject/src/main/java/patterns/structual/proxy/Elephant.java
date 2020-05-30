package patterns.structual.proxy;

public class Elephant implements Animal
{
    @Override
    public void go()
    {
        System.out.println("------------------------\nElephant walks slowly...\n------------------------");
    }
}
