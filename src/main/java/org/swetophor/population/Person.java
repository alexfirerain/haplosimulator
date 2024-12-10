package org.swetophor.population;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.swetophor.haplotree.Haplotype;

public abstract class Person {
    public final long id;
    protected final Haplotype yHaplotype;
    protected final Haplotype mtHaplotype;


    protected Person(long id, Haplotype yHaplotype, Haplotype mtHaplotype) {
        this.id = id;
        this.yHaplotype = yHaplotype;
        this.mtHaplotype = mtHaplotype;
    }
}
