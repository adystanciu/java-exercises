package stady.com.java8study.designpatterns.structural.proxy;

public class RealImage implements  Image{
    String name;

    public RealImage(String name) {
        this.name = name;
        this.loadFromDisk(name);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + name);
    }

    private void loadFromDisk(String name){
        System.out.println("Loading " + name);
    }
}
