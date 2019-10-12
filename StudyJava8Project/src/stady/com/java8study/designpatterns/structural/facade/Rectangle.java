package stady.com.java8study.designpatterns.structural.facade;

public class Rectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("RECTANGLE::draw");
    }
}
