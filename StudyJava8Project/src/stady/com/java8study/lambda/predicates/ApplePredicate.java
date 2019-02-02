package stady.com.java8study.lambda.predicates;

import stady.com.java8study.lambda.Apple;

@FunctionalInterface
public interface ApplePredicate {
    boolean test(Apple apple);
}
