package stady.com.java8study.designpatterns.structural.proxy;

public class ProxyMain {

    public static void main(String[] args) {
        Image image = new ImageProxy("img1.jpg");
        image.display();
        System.out.println("Now, the load method will not be accessed");
        image.display();
    }
}
