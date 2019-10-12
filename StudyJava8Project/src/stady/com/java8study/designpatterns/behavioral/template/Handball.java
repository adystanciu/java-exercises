package stady.com.java8study.designpatterns.behavioral.template;

public class Handball extends Game {

    @Override
    public void gameInitialization() {
        System.out.println(" Handball init");
    }

    @Override
    public void gameStart() {
        System.out.println("Start handball game");
    }

    @Override
    public void gameEnd() {
        System.out.println("end handball match!");
    }
}
