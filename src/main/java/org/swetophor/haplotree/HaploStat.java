package org.swetophor.haplotree;

import org.swetophor.population.Folk;
import org.swetophor.population.Individual;

import java.util.HashMap;
import java.util.Map;

/**
 * Статистика присутствия гаплотипов в популяции.
 */
public class HaploStat {
    private final Map<Haplotype, Integer> haplotypes;
    private final Map<String, Integer> mutations;
    private long actualSize;

    public HaploStat() {
        haplotypes = new HashMap<>();
        mutations = new HashMap<>();
    }

    public void addIndividual(Haplotype ofType) {
        if (!haplotypes.containsKey(ofType)) haplotypes.put(ofType, 0);
        haplotypes.put(ofType, haplotypes.get(ofType) + 1);
        if (!mutations.containsKey(ofType.getTitle())) mutations.put(ofType.getTitle(), 0);
        ofType.getMutations()
                .forEach(mutation -> mutations.put(mutation, mutations.get(mutation) + 1));
        actualSize++;
    }

    public void subtractIndividual(Haplotype ofType) {
        if (!haplotypes.containsKey(ofType)) return;
        haplotypes.put(ofType, haplotypes.get(ofType) - 1);
        ofType.getMutations()
                .forEach(mutation -> mutations.put(mutation, mutations.get(mutation) - 1));
        actualSize--;
    }

    public void showStat() {
        System.out.println("Размер популяции: " + actualSize);  // TODO: разнести генерацию текста и вывод
        System.out.println("Гаплотипы:");
        for (Map.Entry<Haplotype, Integer> type : haplotypes.entrySet()) {
            Haplotype next = type.getKey();
            System.out.printf("\t%s\t- %s%n",
                    next.getTitle(), percentageFor(next));
        }
        System.out.println("Мутации:");
        for (Map.Entry<String, Integer> mutation : mutations.entrySet()) {
            String next = mutation.getKey();
            System.out.printf("\t%s\t- %s%n",
                    next, percentageFor(next));
        }
    }

    private String percentageFor(String ofMutations) {
        Integer value = mutations.get(ofMutations);
        if (value == null || value == 0) return "–";
        return "%s%%".formatted(String.format("%2.0f", (double) value * 100 / actualSize));
    }

//    private String

    public String percentageFor(Haplotype ofType) {
        Integer value = haplotypes.get(ofType);
        if (value == null || value == 0) return "–";
        return "%s%%".formatted(String.format("%2.0f", (double) value * 100 / actualSize));
    }

    public void register(Individual personToCome) {
        addIndividual(personToCome.getHaplotype());
    }

    public void exclude(Individual personToLeave) {
        subtractIndividual(personToLeave.getHaplotype());
    }

    public void update(Folk folk) {
        for (Individual person : folk.getLiving()) addIndividual(person.getHaplotype());
        for (Individual ancestor : folk.getAncestors()) addIndividual(ancestor.getHaplotype());
    }
}
