package patterns.structual.proxy;

public class ProxyElephant implements Animal
{

    private Animal animal;

    public ProxyElephant(Animal animal)
    {
        this.animal = animal;
    }

    @Override
    public void go()
    {
        long start = System.nanoTime();
        System.out.println("start time: " + start);
        animal.go();
        long end = System.nanoTime();
        System.out.println("end time: " + end);
        System.out.println("spent time: " + (end - start)/100 + "mcs");
    }
}
