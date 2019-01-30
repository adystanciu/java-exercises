package stady.com.java8study.patterns.behavior_parameterization;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AppleMain {

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple("green", 20), new Apple("yellow", 150), new Apple("red", 200));
//        FillterApples.filterGreenApples(inventory).forEach(System.out::println);
//        FillterApples.filterApplesByColor(inventory, "red").forEach(System.out::println);
//
//        FillterApples.filterApplesByWeight(inventory, 100.0).forEach(System.out::println);

//        BETTER
//        FillterApples.fillterApples(inventory,new AppleGreenColorPredicate()).forEach(System.out::println);
//        FillterApples.fillterApples(inventory, new AppleHeavyWeightPredicate()).forEach(System.out::println);
//
//        Anonymous classes !!! Match better: Because now is more flexible
//        FillterApples.fillterApples(inventory, new ApplePredicate() {
//            @Override
//            public boolean test(Apple apple) {
//                return ( !apple.getColor().equals("yellow") && apple.getWeight() > 100 );
//            }
//        }).forEach(System.out::println);

        // Best option: LAMBDA!!!!!!!!!!
//        FillterApples.fillterApples(inventory, (Apple a) -> "yellow".equals(a.getColor())).forEach(System.out::println);
//        FillterApples.fillterApples(inventory, (Apple a) -> a.getWeight()<50).forEach(System.out::println);
//        FillterApples.fillterApples(inventory, a -> "yellow".equals(a.getColor()) || a.getWeight() > 170).forEach(System.out::println);


        // REAL WORLD EXAMPLES:

        // 1 Comparator:
//        inventory.sort(new Comparator<Apple>() {
//            public int compare(Apple a1, Apple a2){
//                return a1.getWeight()>a2.getWeight()? -1 : 1;
//            }
//        });
//        inventory.forEach(System.out::println);
//        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() > a2.getWeight() ? -1 : 1);
//        inventory.forEach(System.out::println);

//        2 Threads:
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                System.out.println("Hello world");
//            }
//        });
//        t.start();
//
//        Thread t1 = new Thread(() ->System.out.println("Hello world from Lambda!"));
//        t1.start();
    }
}

/*
*               SUMMARY: LAMBDA ULTIMATE ATTACK
*
    1) Behavior parameterization is the ability for a method to take multiple different behaviors as parameters and use them internally to accomplish different behaviors.
    2) Behavior parameterization lets you make your code more adaptive to changing requirements and saves on engineering efforts in the future.
    3) Passing code is a way to give new behaviors as arguments to a method. But it’s verbose prior to Java 8.
    Anonymous classes helped a bit before Java 8 to get rid of the verbosity associated with declaring multiple concrete classes for an interface that are needed only once.
    4) The Java API contains many methods that can be parameterized with different behaviors, which include sorting, threads, and GUI handling.

*
*
* */
