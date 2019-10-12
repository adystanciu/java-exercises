package stady.com.java8study.designpatterns.structural.facade;

public class FacadeMain {

    public static void main(String[] args) {
        ShapeFacadeMaker shapeMaker = new ShapeFacadeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
