package stady.com.java8study.designpatterns.behavioral.template;

public class TemplateMain {

    public static void main(String[] args) {
        Game football = new Football();
        Game handball = new Handball();

        football.play();
        handball.play();
    }
}
