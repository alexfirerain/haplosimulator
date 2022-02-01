package org.swetophor.population;

import org.swetophor.haplotree.HaploStat;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Folk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Individual> alivePeople;
    private final List<Individual> ancestors;
    private long nextIndex;
    transient private final HaploStat haploStatistic;

    public Folk() {
        alivePeople = new ArrayList<>();
        ancestors = new ArrayList<>();
        nextIndex = 0;
        haploStatistic = new HaploStat();
    }

    public Folk(Individual[] population) {
        alivePeople = new ArrayList<>();
        alivePeople.addAll(Arrays.asList(population));
        ancestors = new ArrayList<>();
        nextIndex = alivePeople.size();
        haploStatistic = new HaploStat();
        haploStatistic.update(this);
    }


    public long totalHeads() {
        return nextIndex;
    }

    public Individual[] getAncestors() {
        return ancestors.toArray(new Individual[0]);
    }

    public Individual[] getAlive() {
        return alivePeople.toArray(new Individual[0]);
    }
}
