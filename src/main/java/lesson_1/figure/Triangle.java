package lesson_1.figure;

public class Triangle extends Figure {

    private final double sideLengthA;
    private final double sideLengthB;
    private final double sideLengthC;

    public Triangle(double sideLengthA, double sideLengthB, double sideLengthC) {
        this.sideLengthA = sideLengthA;
        this.sideLengthB = sideLengthB;
        this.sideLengthC = sideLengthC;
    }

    @Override
    protected double getPerimeter() {
        return sideLengthA + sideLengthB + sideLengthC;
    }

    @Override
    protected double getArea() {
        double halPerimeter = getPerimeter() / 2;
        return Math.sqrt(halPerimeter * (halPerimeter - sideLengthA) * (halPerimeter - sideLengthB) * (halPerimeter - sideLengthC));
    }
}
