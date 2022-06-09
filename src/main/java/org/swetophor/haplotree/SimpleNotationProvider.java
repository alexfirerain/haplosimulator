package org.swetophor.haplotree;

import java.util.Random;
import java.util.Set;

/**
 * Простой поставщик номенклатуры мутаций и гаплогрупп.
 * Уникальное обозначение новой мутации формируется из буквы из списка
 * {@code mutationLetters} и случайного числа (не большего,
 * чем всего уже сгенерировано мутаций).
 * Очередная буква для номенклатуры гаплогрупп берётся по порядку
 * из списка {@code haplogroupSource}, по их исчерпанию буквы начинают
 * выдаваться снова с начала с добавлением штриха.
 * Поставщик только выдаёт следующую букву для номенклатуры.
 * Буквенно-числовое обозначение должно порождаться Древом Гаплогрупп.
 */
public class SimpleNotationProvider implements NotationProvider {
    /*
    Перечни букв можно грузить из внешнего файла. Сейчас они вшиты.
     */

    private static final char[] haplogroupSource = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
                                                'H', 'I', 'J', 'K', 'L', 'M', 'N' };
    private static final char[] mutationLetters = { 'M', 'Z', 'V', 'M', 'P' };

    private static final Random r = new Random();

    private Set<String> mutationPool;
    private Set<String> haplogroupPool;


    @Override
    public String nextMutation() {

        String newMutation;
        do {
            newMutation = "%s-%s".formatted(mutationLetters[r.nextInt(mutationLetters.length)],
                                            String.valueOf(r.nextInt(mutationPool.size() + 1)));
        } while (mutationPool.contains(newMutation));
        mutationPool.add(newMutation);

        return newMutation;
    }

    @Override
    public String nextHaplogroupLetter() {
        String nextHGNotation = String.valueOf(
                haplogroupSource[haplogroupPool.size() % haplogroupSource.length]);

        while (haplogroupPool.contains(nextHGNotation)) {
            nextHGNotation += "'";
        }
        haplogroupPool.add(nextHGNotation);

        return nextHGNotation;
    }
}
