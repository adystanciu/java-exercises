package stady.com.java8study.designpatterns.creational.factory;

public class Circle implements Shape{

    private int r=10;

    @Override
    public double getArea() {
        return 2 * this.r * Math.PI;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
