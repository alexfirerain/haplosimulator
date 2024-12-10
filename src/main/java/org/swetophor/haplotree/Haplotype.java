package org.swetophor.haplotree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Определённый гаплотип.
 */
public class Haplotype {
    /**
     * Предковый гаплотип, от которого наследуются мутации.
     */
    private final Haplotype ancestor;
    /**
     * Цепочка мутаций, определяющая данный гаплотип от корня.
     */
    private final Deque<String> mutations;
    /**
     * Список гаплотипов, наследующих от данного.
     */
    private final List<Haplotype> descendents;

    /**
     * Создаёт новый гаплотип на основе указанного с введением новой определяющей мутации.
     * @param ancestor      предковый гаплотип.
     * @param titleMutation новая мутация, которая будет именем гаплотипа.
     */
    public Haplotype(Haplotype ancestor, String titleMutation) {
        this.ancestor = ancestor;
        mutations = ancestor.getMutations();
        mutations.add(titleMutation);
        descendents = new LinkedList<>();
    }

    /**
     * Создаёт новый корневой гаплотип на основе новой указанной мутации.
     * @param titleMutation новая мутация, которая будет именем гаплотипа.
     */
    public Haplotype(String titleMutation) {
        ancestor = null;
        mutations = new LinkedList<>();
        mutations.add(titleMutation);
        descendents = new LinkedList<>();
    }

    /**
     * Отпочковывает и возвращает от гаплотипа новый гаплотип с указанной мутацией.
     * @param titleMutation новая мутация, которая будет именем нового гаплотипа.
     * @return  новый отпочкованный гаплотип.
     */
    public Haplotype stem(String titleMutation) {
        Haplotype offspring = new Haplotype(this, titleMutation);
        descendents.add(offspring);
        return offspring;
    }

    /**
     * Определяющая мутация данного гаплотипа.
     * @return название определяющей мутации.
     */
    public String getTitle() {
        return mutations.isEmpty() ? "" : mutations.getLast();
    }

    /**
     * Последовательность мутаций от корня до данного гаплотипа.
     * @return  список наследуемых мутаций в порядке их зарождения.
     */
    public Deque<String> getMutations() {
        return mutations;
    }

    /**
     * Гаплотип, предковый для данного.
     * @return предковый гаплотип.
     */
    public Haplotype getAncestor() {
        return ancestor;
    }

    /**
     * Список гаплотипов, наследующих от данного.
     * @return список гаплотипов-потомков.
     */
    public List<Haplotype> getDescendents() {
        return descendents;
    }

    /**
     * Входит ли указанная мутация в данный гаплотип.
     * @param mutation проверяемая на присутствие мутация.
     * @return {@code истинно}, если такая мутация характерезует данный тип или его предков.
     */
    public boolean containsMutation(String mutation) {
        return mutations.contains(mutation);
    }

    /**
     * Является ли гаплотип корневым.
     * @return {@code истинно}, если гаплотип не имеет предка.
     */
    public boolean isRoot() {
        return ancestor == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Haplotype haplotype = (Haplotype) o;
        return mutations.equals(haplotype.mutations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
