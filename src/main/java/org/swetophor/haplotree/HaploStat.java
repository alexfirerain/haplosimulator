package org.swetophor.haplotree;

import java.util.HashMap;
import java.util.Map;

/**
 * Статистика присутствия гаплотипов в популяции.
 */
public class HaploStat {
    private final Map<Haplotype, Integer> haplotypes;
    private long actualSize;

    public HaploStat() {
        haplotypes = new HashMap<>();
    }

    public void addIndividual(Haplotype ofType) {
        if (!haplotypes.containsKey(ofType)) haplotypes.put(ofType, 0);
        haplotypes.put(ofType, haplotypes.get(ofType) + 1);
        actualSize++;
    }

    public void subtractIndividual(Haplotype ofType) {
        if (!haplotypes.containsKey(ofType)) return;
        haplotypes.put(ofType, haplotypes.get(ofType) - 1);
        actualSize--;
    }

    public void showStat() {
        System.out.println("Размер популяции: " + actualSize);
        for (Map.Entry<Haplotype, Integer> type : haplotypes.entrySet()) {
            Haplotype next = type.getKey();
            System.out.printf("\t%s\t- %s%n",
                    next.getTitle(), percentageFor(next));
        }
    }

    public String percentageFor(Haplotype ofType) {
        Integer value = haplotypes.get(ofType);
        if (value == null || value == 0) return "–";
        return String.format("%2.0f", (double) value * 100 / actualSize) + "%";
    }
}
