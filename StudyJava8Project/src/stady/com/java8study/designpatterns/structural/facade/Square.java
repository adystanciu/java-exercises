package stady.com.java8study.designpatterns.structural.facade;

public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("SQUARE::draw");
    }
}
