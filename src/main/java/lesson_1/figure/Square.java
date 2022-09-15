package lesson_1.figure;

public class Square extends Figure {

    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    protected double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    protected double getArea() {
        return Math.pow(sideLength, 2);
    }
}
