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
                .forEach(z -> {
                    if (!ageStat.containsKey(z))
                        ageStat.put(z, 1);
                    else
                        ageStat.put(z, ageStat.get(z) + 1);
                });

        for (Map.Entry<Integer, Integer> value : ageStat.entrySet())
            System.out.printf("%d год/лет: %d чел.%n", value.getKey(), value.getValue());

        for (int i = 0; i <= ageStat.lastKey(); i++) {
            System.out.printf("%n%3d: ", i);
            if (ageStat.containsKey(i))
                plot(ageStat.get(i), "§");
        }

    }

    private static void plot(int amount, String symbol) {
        IntStream.range(0, amount).mapToObj(i -> symbol).forEach(System.out::print);
    }

}
