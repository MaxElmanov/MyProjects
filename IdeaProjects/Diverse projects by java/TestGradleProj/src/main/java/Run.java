public class Run
{
    public static void main(String[] args)
    {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello");
    }
}
