package org.swetophor.haplotree;

import java.util.LinkedList;
import java.util.List;

public class Haplogroup {
    private final String notation;
    private final String haplotype;
    private final Haplogroup ancestor;
    private List<Haplogroup> descendents;

    public Haplogroup(String notation, String haplotype, Haplogroup ancestor) {
        this.notation = notation;
        this.haplotype = haplotype;
        this.ancestor = ancestor;
        descendents = new LinkedList<>();
    }

    public static Haplogroup rootGroup() {
        return new Haplogroup("ROOT", "0", null);
    }

    public Haplogroup divide(String newName, String forType) {
        Haplogroup newGroup = new Haplogroup(newName, forType, this);
        descendents.add(newGroup);
        return newGroup;
    }
}
