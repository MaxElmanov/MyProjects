package patterns.creational.builder;

public class Lada implements Car {

    //crucial (required variables)
    private String name;
    private int amountSafetyBags;

    //optional variables
    private boolean isNew;
    private String color;

    public Lada(LadaBuilder builder) {
        this.name = builder.name;
        this.amountSafetyBags = builder.amountSafetyBags;
        this.isNew = builder.isNew;
        this.color = builder.color;
    }

    public static class LadaBuilder{

        private String name;
        private int amountSafetyBags;

        private boolean isNew;
        private String color;

        public LadaBuilder(String name, int amountSafetyBags) {
            this.name = name;
            this.amountSafetyBags = amountSafetyBags;
        }

        public LadaBuilder setIsNew(boolean aNew) {
            isNew = aNew;
            return this;
        }

        public LadaBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public Lada build(){
            return new Lada(this);
        }
    }

    @Override
    public void run() {
        System.out.println("Lada "+ this.name + " is going");
    }

    @Override
    public String toString() {
        return "Lada{" +
                "name='" + name + '\'' +
                ", amountSafetyBags=" + amountSafetyBags +
                ", isNew=" + isNew +
                ", color='" + color + '\'' +
                '}';
    }
}
