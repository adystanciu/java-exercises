package stady.com.java8study.designpatterns.structural.proxy;

public class ImageProxy implements Image{
    String name;
    RealImage realImage;

    public ImageProxy(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        if (realImage == null) realImage = new RealImage(name);
        System.out.println("Here is a proxy!");
        realImage.display();
    }
}
