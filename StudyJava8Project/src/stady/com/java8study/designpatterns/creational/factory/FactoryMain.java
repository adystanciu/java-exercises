package stady.com.java8study.designpatterns.creational.factory;

public class FactoryMain {

    public static void main(String[] args) {

        Shape circle = ShapeFactory.getInstance("circle");
        System.out.println(circle.getArea());
        Shape rectangle = ShapeFactory.getInstance("rectangle");
        System.out.println(rectangle.getArea());
        Shape square = ShapeFactory.getInstance("square");
        System.out.println(square.getArea());
    }
}
