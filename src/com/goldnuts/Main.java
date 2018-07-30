package com.goldnuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<Human> firstGroup;
    private static Integer maxW = 0;
    private static Integer bestSum;

    public static void main(String[] args) {

        Human human1 = new Human(9);
        Human human2 = new Human(10);
        Human human3 = new Human(20);
        Human human4 = new Human(6);
        Human human5 = new Human(11);
        Human human6 = new Human(40);
        Human human7  = new Human(16);

        List<Human> humans = Arrays.asList(human1, human2, human3, human4, human5, human6, human7);
        maxW = calcWeight(humans)/2;
        makeAllSets(humans);
        List<Human> secondGroup = createSecondGroup(humans, firstGroup);

        firstGroup.forEach(human -> System.out.print(human.getWeight() + " "));
        System.out.println();
        secondGroup.forEach(human -> System.out.print(human.getWeight() + " "));

    }

    private static List<Human> createSecondGroup(List<Human> humans, List<Human> groupByWeight1) {
        return humans.stream().filter(human -> !groupByWeight1.contains(human))
                .collect(Collectors.toList());
    }

    private static Integer calcWeight(List<Human> humans) {
        final Integer[] sum = {0};
        humans.forEach(item -> sum[0] += item.getWeight());
        return sum[0];
    }

    private static void checkSet(List<Human> humans) {
        if (firstGroup == null) {
            if (calcWeight(humans) <= maxW) {
                firstGroup = humans;
                bestSum = calcWeight(humans);
            }
        } else {
            if (calcWeight(humans) <= maxW && calcWeight(humans) > bestSum) {
                firstGroup = humans;
                bestSum = calcWeight(humans);
            }
        }

    }

    private static void makeAllSets(List<Human> humans) {


        if (humans.size() > 0)
            checkSet(humans);
        for (int i = 0; i < humans.size(); i++) {
            List<Human> newSet = new ArrayList<>(humans);
            newSet.remove(i);
            makeAllSets(newSet);
        }

    }

}
