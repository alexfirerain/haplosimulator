package org.swetophor.haplotree;

/**
 * Поставщик номенклатуры мутаций и гаплогрупп.
 * Предоставляет метод для генерации уникальных имён мутаций
 * и метод для определения очередной буквы в Древе Гаплогрупп.
 * Поставщик только выдаёт следующую букву для номенклатуры.
 * Буквенно-числовое обозначение должно порождаться Древом Гаплогрупп.
 */
public interface NotationProvider {
    String nextMutation();

    String nextHaplogroupLetter();

}