package stady.com.java8study.designpatterns.creational.factory;

public class Square implements Shape {

    private int l=10;

    @Override
    public double getArea() {
        return l*l;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }
}
