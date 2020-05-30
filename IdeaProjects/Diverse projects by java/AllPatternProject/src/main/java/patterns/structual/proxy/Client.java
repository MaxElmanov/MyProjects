package patterns.structual.proxy;

public class Client
{
    public static void main(String[] args)
    {
        Elephant elephant = new Elephant();
        elephant.go();

        System.out.println();

        ProxyElephant proxyElephant = new ProxyElephant(elephant);
        proxyElephant.go();
    }
}
