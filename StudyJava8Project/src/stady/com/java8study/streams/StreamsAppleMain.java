package stady.com.java8study.streams;

import stady.com.java8study.lambda.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamsAppleMain {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 200), new Apple("red", 100),
                new Apple("yellow", 155), new Apple("burgundy", 150));
        List<String> lightWeightApplesName = apples
//                .stream()
                .parallelStream()
                .filter((Apple a) -> a.getWeight() < 170)
//                .sorted((a1, a2) -> a1.getWeight() < a2.getWeight()? -1 : 1)
                .sorted(comparing(a -> a.getWeight())) // Apple:getWeight
                .map(Apple::getColor)
                .collect(Collectors.toList());
        lightWeightApplesName.forEach(System.out::println);
    }

}
