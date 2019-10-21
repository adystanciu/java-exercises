package stady.com.java8study.designpatterns.structural.facade;

public class ShapeFacadeMaker {

    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeFacadeMaker() {
        this.circle = new Circle();
        this.rectangle = new Rectangle();
        this.square = new Square();
    }

    public void drawCircle(){ circle.draw(); }

    public void drawRectangle(){
        rectangle.draw();
    }

    public void drawSquare(){
        square.draw();
    }
}
