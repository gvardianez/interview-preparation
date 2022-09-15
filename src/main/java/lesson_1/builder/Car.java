package lesson_1.builder;

public class Car {

    private final String engine;
    private final String transmission;
    private final String body;
    private final String brakes;
    private final String pedals;

    public Car(String engine, String transmission, String body, String brakes, String pedals) {
        this.engine = engine;
        this.transmission = transmission;
        this.body = body;
        this.brakes = brakes;
        this.pedals = pedals;
    }

    public static class Builder {

        private String engine;
        private String transmission;
        private String body;
        private String brakes;
        private String pedals;

        public Builder addEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Builder addTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        public Builder addBody(String body) {
            this.body = body;
            return this;
        }

        public Builder addBrakes(String brakes) {
            this.brakes = brakes;
            return this;
        }

        public Builder addPedals(String pedals) {
            this.pedals = pedals;
            return this;
        }

        public Car build() {
            return new Car(engine, transmission, body, brakes, pedals);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                ", body='" + body + '\'' +
                ", brakes='" + brakes + '\'' +
                ", pedals='" + pedals + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Car c = new Car.Builder().addEngine("asd").addPedals("asdsad").build();
        System.out.println(c);
    }
}
