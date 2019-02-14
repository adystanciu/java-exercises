package stady.com.java8study.streams;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class StreamsDishMain {
    public enum CaloricLevel {FAT, NORMAL, DIET};

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, DishType.MEAT),
                new Dish("beef", false, 700, DishType.MEAT),
                new Dish("chicken", false, 400, DishType.MEAT),
                new Dish("french fries", true, 530, DishType.OTHER),
                new Dish("rice", true, 350, DishType.OTHER),
                new Dish("season fruit", true, 120, DishType.OTHER),
                new Dish("pizza", true, 550, DishType.OTHER),
                new Dish("prawns", false, 300, DishType.FISH),
                new Dish("salmon", false, 450, DishType.FISH) );


        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
//        System.out.println(mostCalorieDish.get().getName() + " " +mostCalorieDish.get().getCalories());

        int totalCaloriesMenu = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        int totalCalories = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        int totalCalories1 = menu.stream().mapToInt(Dish::getCalories).sum();
//        System.out.println("totalCaloriesMenu: " +totalCaloriesMenu);
        double averageCaloriesMenu = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
//        System.out.println("averageCaloriesMenu: " +averageCaloriesMenu);

        IntSummaryStatistics statistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
//        System.out.println(statistics);
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
//        System.out.println(shortMenu);
        Map<DishType, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getDishType));

        // SUB GROUP
        Map<DishType,Map<CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel = menu.stream().collect(Collectors.groupingBy(Dish::getDishType, Collectors.groupingBy(
                (Dish d) -> {
                    if (d.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (d.getCalories() < 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }
        )));
        dishesByTypeAndCaloricLevel.forEach((dishType, dishesByCaloricLevel) -> {
            System.out.println(dishType + "\n{");
            dishesByCaloricLevel.forEach((caloricLevel, dishes) -> {
                System.out.println(caloricLevel.toString() + " " + dishes.toString());
                }
            );
            System.out.println("}");
        });

         menu.stream().collect(
                groupingBy(Dish::getDishType, counting())).forEach((dishType, aLong) -> System.out.println(dishType.toString() + ":" + aLong));
    }
}
