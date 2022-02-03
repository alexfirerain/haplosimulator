package org.swetophor.simulator;

import org.swetophor.population.Folk;
import org.swetophor.population.Individual;

import java.util.Arrays;

public class HaploSimulator {
    public static void main(String[] args) {
        Folk population = new Folk(100, 60);
        population.haploStatistic.showStat();
        Arrays.stream(population.getLiving()).map(Individual::getAge).forEach(System.out::println);
    }

}
