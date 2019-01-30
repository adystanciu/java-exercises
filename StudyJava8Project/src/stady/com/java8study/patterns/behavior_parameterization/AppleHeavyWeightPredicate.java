package stady.com.java8study.patterns.behavior_parameterization;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 120;
    }
}
