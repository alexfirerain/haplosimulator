package org.swetophor.population;

import lombok.AllArgsConstructor;
import org.swetophor.haplotree.Haplotype;

@AllArgsConstructor
public abstract class Person {
    public final long id;
    protected final Haplotype yHaplotype;
    protected final Haplotype mtHaplotype;


}
