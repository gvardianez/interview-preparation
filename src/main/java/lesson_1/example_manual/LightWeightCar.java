package lesson_1.example_manual;

public class LightWeightCar extends Car {

    @Override
    public void open() {
        System.out.println("LightWeightCar is open");
    }

    @Override
    public void move() {
        System.out.println("LightWeightCar is moving");
    }


}
