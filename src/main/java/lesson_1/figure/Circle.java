package lesson_1.figure;

public class Circle extends Figure {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    protected double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    protected double getArea() {
        return Math.pow(radius, 2) * Math.PI;
    }
}
