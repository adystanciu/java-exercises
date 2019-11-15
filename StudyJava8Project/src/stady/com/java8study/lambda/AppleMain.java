package stady.com.java8study.lambda;

import stady.com.java8study.lambda.patterns.behavior_parameterization.FillterApples;

import java.sql.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AppleMain {

    public static void main(String[] args) throws ParseException {

        List<Apple> inventory = Arrays.asList(new Apple("green", 20), new Apple("yellow", 150), new Apple("red", 200));
        FillterApples.filterGreenApples(inventory).forEach(System.out::println);
        FillterApples.filterApplesByColor(inventory, "red").forEach(System.out::println);

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

        inventory.sort(Comparator
                .comparing(Apple::getWeight)
                .reversed()
                .thenComparing((Apple a) -> a.getColor())); // Apple::getColor

        Apple testApple = new Apple("red", 300);

        Predicate<Apple> p = (Apple a) -> !"red".equals(a.getColor());
        boolean isNotRed = p.test(testApple);

        p.and(a -> a.getWeight()>100)
                .negate()
                .or(a -> "yellow".equals(a.getColor()));

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

        String startDate="2019-05-12";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(1573554506122L);
//        System.out.println(d.getTime());
        System.out.println("TS: " +sqlStartDate);

//        Date date = new Date(1555027200000L);
//        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
//        System.out.println(format.format(date));


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("uuuu-MM-dd HH:mm:ss.SSS");
        String text = "2019-10-30 14:43:58.222";
        LocalDateTime dateTime = LocalDateTime.parse(text, dtf);
        System.out.println(dateTime);

    }


}

/*
*               SUMMARY: LAMBDA ULTIMATE ATTACK.. Lambda - anonymous function
*
    1) Behavior parameterization is the ability for a method to take multiple different behaviors as parameters and use them internally to accomplish different behaviors.
    2) Behavior parameterization lets you make your code more adaptive to changing requirements and saves on engineering efforts in the future.
    3) Passing code is a way to give new behaviors as arguments to a method. But it’s verbose prior to Java 8.
    Anonymous classes helped a bit before Java 8 to get rid of the verbosity associated with declaring multiple concrete classes for an interface that are needed only once.
    4) The Java API contains many methods that can be parameterized with different behaviors, which include sorting, threads, and GUI handling.
*
*
* */
