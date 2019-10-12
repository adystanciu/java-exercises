package stady.com.java8study.designpatterns.behavioral.strategy;

public class ContextOfStrategy {

    OpNumStrategy strategy;

    public ContextOfStrategy(OpNumStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(int no1, int no2){
        System.out.println(strategy.opNum(no1,no2));
    }
}
