package org.swetophor.simulator;

import org.swetophor.population.Folk;
import org.swetophor.population.Individual;

import java.util.*;
import java.util.stream.IntStream;

public class HaploSimulator {
    public static void main(String[] args) {
        Folk population = new Folk(100, 60);
        System.out.println(population.haploStatistic.livingStat());

        NavigableMap<Integer,Integer> ageStat = new TreeMap<>();

        Arrays.stream(population.getLiving())
                .map(Individual::getAge)
                .forEach(z -> ageStat.merge(z, 1, Integer::sum));

        ageStat.forEach((key, value1) ->
                System.out.printf("%d год/лет: %d чел.%n", key, value1));

        for (int i = 0; i <= ageStat.lastKey(); i++) {
            System.out.printf("%n%3d: ", i);
            if (ageStat.containsKey(i))
                plot(ageStat.get(i));
        }

    }

    private static void plot(int amount, char symbol) {
        System.out.println(String.valueOf(symbol).repeat(amount));
    }

    private static void plot(int amount) {
        plot(amount, '§');
    }

}
