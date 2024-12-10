package org.swetophor.haplotree;

import lombok.NoArgsConstructor;
import org.swetophor.population.Folk;
import org.swetophor.population.Individual;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Статистика присутствия гаплотипов в популяции.
 */
@NoArgsConstructor
public class HaploStat {
    /**
     * Счётчик носителей гаплотипов
     */
    private final Map<Haplotype, Integer> haplotypes = new HashMap<>();
    /**
     * Счётчик носителей конкретных мутаций
     */
    private final Map<String, Integer> mutations = new HashMap<>();
    /**
     * Счётчик актуального размера популяции
     */
    private long actualSize = 0;

    public HaploStat(Individual[] population) {
        this();
        Arrays.stream(population).forEach(this::register);
    }

    /**
     * Учитывает в Статистике гаплотип и мутации нового индивида.
     * @param ofType гаплотип, несомый новым индивидом.
     */
    public void addIndividual(Haplotype ofType) {
        haplotypes.put(ofType,
                haplotypes.containsKey(ofType) ?
                        haplotypes.get(ofType) + 1 :
                        1);


        if (!mutations.containsKey(ofType.getTitle()))
            mutations.put(ofType.getTitle(), 0);

        ofType.getMutations()
                .forEach(mutation -> mutations.put(mutation, mutations.get(mutation) + 1));

        actualSize++;
    }

    /**
     * Изымает гаплотип и мутации индивида из Статистики.
     * @param ofType гаплотип, несомый изымаемым индивидом.
     */
    public void subtractIndividual(Haplotype ofType) {
        if (!haplotypes.containsKey(ofType)) return;
        haplotypes.put(ofType, haplotypes.get(ofType) - 1);
        ofType.getMutations()
                .forEach(mutation -> mutations.put(mutation, mutations.get(mutation) - 1));
        actualSize--;
    }

    /**
     * Сообщает состояние статистики на текущий момент
     * @return строку с размером популяции, количеством актуальных носителей
     * каждого гаплотипа и каждой мутации.
     */
    public String livingStat() {
        StringBuilder report = new StringBuilder();
        report.append("Размер популяции: %d%n".formatted(actualSize));
        report.append("Гаплотипы:\n");
        for (Map.Entry<Haplotype, Integer> type : haplotypes.entrySet()) {
            Haplotype next = type.getKey();
            report.append("\t%s\t- %s%n"
                    .formatted(next.getTitle(), percentageFor(next)));
        }
        report.append("Мутации:\n");
        for (Map.Entry<String, Integer> mutation : mutations.entrySet()) {
            String next = mutation.getKey();
            report.append("\t%s\t- %s%n".
                    formatted(next, percentageFor(next)));
        }
        // TODO: также представлять статистику и по гаплогруппам, когда будут реализованы
        return report.toString();
    }


    private String percentageFor(String ofMutations) {
        Integer value = mutations.get(ofMutations);
        if (value == null || value == 0) return "–";
        return "%s%%".formatted(String.format("%2.0f", (double) value * 100 / actualSize));
    }

    public String percentageFor(Haplotype ofType) {
        Integer value = haplotypes.get(ofType);
        if (value == null || value == 0) return "–";
        return "%s%%".formatted(String.format("%2.0f", (double) value * 100 / actualSize));
    }

    /**
     * Учитывает в Статистике нового индивида.
     * @param personToCome прибывающий индивид.
     */
    public void register(Individual personToCome) {
        addIndividual(personToCome.getHaplotype());
    }

    /**
     * Изымает индивида из Статистики.
     * @param personToLeave уходящий индивид.
     */
    public void exclude(Individual personToLeave) {
        subtractIndividual(personToLeave.getHaplotype());
    }

    public void update(Folk folk) {
        Arrays.stream(folk.getLiving())
                .forEach(this::register);

//        for (Individual ancestor : folk.getAncestors()) register(ancestor);
    }
}
