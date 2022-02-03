package org.swetophor.population;

import org.swetophor.haplotree.HaploStat;
import org.swetophor.haplotree.Haplotype;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Folk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Individual> living;
    private final List<Individual> ancestors;
    private long heads;         // синонимично nextIndex
    transient public final HaploStat haploStatistic;

    public Folk() {
        living = new ArrayList<>();
        ancestors = new ArrayList<>();
        heads = 0;
        haploStatistic = new HaploStat();
    }

    public Folk(Individual[] population) {
        living = Arrays.asList(population);
        ancestors = new ArrayList<>();
        heads = living.size();
        haploStatistic = new HaploStat();
        haploStatistic.update(this);
    }
    public Folk(Individual[] population, Individual[] ancestors) {
        living = Arrays.asList(population);
        this.ancestors = Arrays.asList(ancestors);
        heads = living.size() + this.ancestors.size();
        haploStatistic = new HaploStat();
        haploStatistic.update(this);
    }

    public Folk(int popSize, int lifeSpan) {
        ancestors = new ArrayList<>();
        heads = popSize;
        haploStatistic = new HaploStat();
        living = Arrays.asList(generatePopulation(popSize, lifeSpan, haploStatistic));
        haploStatistic.update(this);
    }

    public static Individual[] generatePopulation(int popSize, int lifeSpan, HaploStat statBase) {
        Individual[] folk = new Individual[popSize];
        Haplotype rootHaplotype = new Haplotype(newMutation());
        for (int i = 0; i < popSize; i++)
            folk[i] = new Individual(randomAge(lifeSpan), rootHaplotype, statBase);
        return folk;
    }

    private static int randomAge(int lifeSpan) {
        int age;
        double chance;
        do {
            age = (int) Math.round(lifeSpan * 2 * Math.random());
            if (age >= lifeSpan) {
                chance = 0.3 * (1 - (double) (age - lifeSpan) / lifeSpan);
            } else if (age >= 3) {
                chance = 0.6 - 0.3 * ((1 - (double) (lifeSpan - age) / lifeSpan));
            } else {
                chance = 1 - 0.2 * (3 - age);
            }
        } while (Math.random() > chance);
        return age;
    }

    private static String newMutation() {
        return "X";
    }


    public long getHeads() {
        return heads;
    }

    public Individual[] getAncestors() {
        return ancestors.toArray(new Individual[0]);
    }

    public Individual[] getLiving() {
        return living.toArray(new Individual[0]);
    }
}
