package stady.com.java8study.designpatterns.behavioral.template;

public abstract class Game {

    public abstract void gameInitialization();
    public abstract void gameStart();
    public abstract void gameEnd();

    final public void play(){
        this.gameInitialization();
        this.gameStart();
        this.gameEnd();
    }
}
