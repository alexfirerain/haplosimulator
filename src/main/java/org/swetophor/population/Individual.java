package org.swetophor.population;

import org.swetophor.haplotree.HaploStat;
import org.swetophor.haplotree.Haplotype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Individual {
    /**
     * Статистическая константа: на сколько приплодов приходится один мультиплет.
     */
    private static final int MULTIPLEX_FACTOR = 20;
    /**
     * Возраст индивида в текущем году.
     */
    private int age;
    /**
     * Гаплотип, несомый индивидом.
     */
    private final Haplotype haplotype;

    /**
     * База статистики, к учёту в которой привязан индивид.
     */
    private final HaploStat base;

    public Individual(int age, Haplotype haplotype, HaploStat base) {
        this.age = age;
        this.haplotype = haplotype;
        this.base = base;
    }

    /**
     * Имитирует жизнь индивида в течение года:
     * <li>увеличивается возраст,</li>
     * <li>если суждено, регистрируется потомство,</li>
     * <li>если суждено, уходит из популяции.</li></li>
     */
    public void liveAYear() {
        age++;
        if (willBeget())
            register(beget());
        if (willExit())
            exclude();
    }

    /**
     * Изымает этого индивида из статистики.
     */
    private void exclude() {
        base.exclude(this);
    }

    /**
     * Сообщает, собирается ли в этом году индивид отправиться к пращурам.
     * @return {@code верно}, если в этом году этот индивид представится, {@code ложно} иначе.
     */
    private boolean willExit() {
        return false;
    }

    /**
     * Сообщает, собирается ли в этом году индивид родить.
     * @return {@code верно}, если в этом году этот индивид даст потомство, {@code ложно} иначе.
     */
    private boolean willBeget() {
        return false;
    }

    private void register(Individual[] beget) {
        Arrays.stream(beget).forEach(base::register);
    }


    /**
     * Возвращает потомство, поставляемое индивидом в популяцию в этом году.
     * @return массив новорожденных (с вероятностью 100 – 100/{@link #MULTIPLEX_FACTOR} %
     * содержит одно дитё, вероятность каждого следующего близнеца в приплоде уменьшается
     * в той же геометрической прогрессии).
     */
    public Individual[] beget() {
        List<Individual> brood = new ArrayList<>(7);
        brood.add(new Individual(0, haplotype, base)); // TODO: вставить мутатор -> в конструктор
        double multiplexProbability = 1.0 / MULTIPLEX_FACTOR;
        while (Math.random() < multiplexProbability) {
            brood.add(new Individual(0, haplotype, base));
            multiplexProbability /= MULTIPLEX_FACTOR;
        }
        return brood.toArray(new Individual[0]);
    }

    public Haplotype getHaplotype() {
        return haplotype;
    }

    public int getAge() {
        return age;
    }
}
