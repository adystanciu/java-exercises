package stady.com.java8study.designpatterns.creational.factory;

public class Rectangle implements Shape {

    private int l=5,L=10;

    @Override
    public double getArea() {
        return l*L;
    }

    public int getL() {
        return l;
    }

    public void setL(int L) {
        this.L = L;
    }

    public int getl() {
        return l;
    }

    public void setl(int l) {
        this.l = l;
    }
}
