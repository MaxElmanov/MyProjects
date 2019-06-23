package patterns.builder;

public class BMW implements Car {

    //crucial (required variables)
    private String name;
    private int amountSafetyBags;

    //optional variables
    private boolean isNew;
    private String color;

    private BMW(BMWBuilder builder){
        this.name = builder.name;
        this.amountSafetyBags = builder.amountSafetyBags;
        this.isNew = builder.isNew;
        this.color = builder.color;
    }

    public static class BMWBuilder{
        //crucial (required variables)
        private String name;
        private int amountSafetyBags;

        //optional variables
        private boolean isNew;
        private String color;

        public BMWBuilder(String name, int amountSafetyBags) {
            this.name = name;
            this.amountSafetyBags = amountSafetyBags;
        }

        public BMWBuilder setIsNew(boolean aNew) {
            isNew = aNew;
            return this;
        }

        public BMWBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public BMW build(){
            return new BMW(this);
        }
    }

    @Override
    public void run() {
        System.out.println("BMW "+ this.name + " is going");
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
