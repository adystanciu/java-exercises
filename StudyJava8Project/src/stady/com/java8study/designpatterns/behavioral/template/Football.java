package stady.com.java8study.designpatterns.behavioral.template;

public class Football extends Game {

    @Override
    public void gameInitialization() {
        System.out.println(" Football init");
    }

    @Override
    public void gameStart() {
        System.out.println("Start football game");
    }

    @Override
    public void gameEnd() {
        System.out.println("end match!");
    }
}
