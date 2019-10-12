package stady.com.java8study.designpatterns.creational.factory;

public class ShapeFactory {

    public static Shape getInstance(String s){
        if (s == null) throw new IllegalArgumentException();

        if("SQUARE".equalsIgnoreCase(s)) return new Square();
        if("CIRCLE".equalsIgnoreCase(s)) return new Circle();
        if("RECTANGLE".equalsIgnoreCase(s)) return new Rectangle();
        return null;
    }
}
