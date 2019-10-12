package stady.com.java8study.designpatterns.structural.facade;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("CIRCLE::draw");
    }
}
