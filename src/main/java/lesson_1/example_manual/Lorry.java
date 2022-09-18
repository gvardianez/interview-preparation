package lesson_1.example_manual;

public class Lorry extends Car {

    @Override
    protected void open() {
        System.out.println("Lorry is open");
    }

    @Override
    public void move() {
        System.out.println("Lorry is moving");
    }

    @Override
    public void stop() {
        System.out.println("Lorry is stop");
    }


}
