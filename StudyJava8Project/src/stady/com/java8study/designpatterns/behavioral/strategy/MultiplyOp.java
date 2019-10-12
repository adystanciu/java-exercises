package stady.com.java8study.designpatterns.behavioral.strategy;

public class MultiplyOp implements OpNumStrategy {

    @Override
    public int opNum(int no1, int no2) {
        return no1 * no2;
    }
}
