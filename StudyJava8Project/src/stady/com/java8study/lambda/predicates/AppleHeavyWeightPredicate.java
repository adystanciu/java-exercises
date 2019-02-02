package stady.com.java8study.lambda.predicates;

import stady.com.java8study.lambda.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 120;
    }
}
