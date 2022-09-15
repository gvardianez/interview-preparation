package lesson_1.example_manual;

public abstract class Car implements Movable, Stopable {

    private Engine engine;
    private String color;
    private String name;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void start() {
        System.out.println("Car starting");
    }

    public void stop() {
        System.out.println("Car stops");
    }

    protected abstract void open();

}
