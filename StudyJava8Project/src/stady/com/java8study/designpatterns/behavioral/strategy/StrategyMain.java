package stady.com.java8study.designpatterns.behavioral.strategy;

public class StrategyMain {

    public static void main(String[] args) {
        OpNumStrategy addStrategy = new AddOp();
        ContextOfStrategy context1 = new ContextOfStrategy(addStrategy);
        context1.executeStrategy(10,7);

        System.out.println("================");

        OpNumStrategy subtstractStrategy = new SubstractOp();
        ContextOfStrategy context2 = new ContextOfStrategy(subtstractStrategy);
        context2.executeStrategy(10,7);

        System.out.println("================");

        OpNumStrategy multiplyStrategy = new MultiplyOp();
        ContextOfStrategy context3 = new ContextOfStrategy(multiplyStrategy);
        context3.executeStrategy(10,7);
    }
}
