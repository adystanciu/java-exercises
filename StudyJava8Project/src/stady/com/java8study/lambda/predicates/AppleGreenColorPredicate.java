package stady.com.java8study.lambda.predicates;

import stady.com.java8study.lambda.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
