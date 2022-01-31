package org.swetophor.population;

import org.swetophor.haplotree.HaploStat;
import org.swetophor.haplotree.Haplotype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Individual {
    private static final int MULTIPLEX_FACTOR = 20;
    private int age;
    private final Haplotype haplotype;
    private final HaploStat base;

    public Individual(int age, Haplotype haplotype, HaploStat base) {
        this.age = age;
        this.haplotype = haplotype;
        this.base = base;
    }

    public void liveAYear() {
        age++;
        if (willBeget())
            register(beget());
        if (willExit())
            exclude();
    }

    private void exclude() {
        base.exclude(this);
    }

    private boolean willExit() {
        return false;
    }

    private boolean willBeget() {
        return false;
    }

    private void register(Individual[] beget) {
        for (Individual creature : beget)
            base.register(creature);
    }


    public Individual[] beget() {
        List<Individual> breed = new ArrayList<>(7);
        breed.add(new Individual(0, haplotype, base)); // TODO: вставить мутатор
        double multiplexProbability = 1.0 / MULTIPLEX_FACTOR;
        while (Math.random() < multiplexProbability) {
            breed.add(new Individual(0, haplotype, base));
            multiplexProbability /= MULTIPLEX_FACTOR;
        }
        return breed.toArray(new Individual[0]);
    }

    public Haplotype getHaplotype() {
        return haplotype;
    }
}
