package patterns.creational.builder;

public class main {
    public static void main(String[] args) {
        Car lada1 = new Lada.LadaBuilder("lada1", 2).setColor("green")
                .setIsNew(true)
                .build();

        Car lada2 = new Lada.LadaBuilder("lada2", 2)
                .setIsNew(false)
                .build();

        System.out.println(lada1);
        System.out.println(lada2);

        lada1.run();
        lada2.run();

        Car bmw1 = new BMW.BMWBuilder("bmw 750i", 5).build();

        Car bmw2 = new BMW.BMWBuilder("bmw X6", 8)
                .setColor("black")
                .setIsNew(true)
                .build();

        System.out.println(bmw1);
        System.out.println(bmw2);

        bmw1.run();
        bmw2.run();

    }
}
