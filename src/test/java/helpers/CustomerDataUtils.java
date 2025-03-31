package helpers;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для совершения операций над данными клиентов
 */
public class CustomerDataUtils {

    /**
     * Вычисление среднего значения длин имен
     * @param names список имен клиентов
     * @return среднее арифметическое длин
     */
    public static double calculateAverageLength(List<String> names) {
        return names.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);
    }

    /**
     * Поиск имени, ближайшего к среднему значению
     * @param names список имен
     * @param averageLength среднее арифметическое длин имен
     * @return первое ближайшее имя к среднему арифметическому
     */
    public static String findClosestName(List<String> names, double averageLength) {
        return names.stream()
                .min(Comparator.comparingDouble(s -> Math.abs(s.length() - averageLength)))
                .orElse(null);
    }

    /**
     * Сравнение двух строк в алфавитном порядке
     * @param first первая строка
     * @param second вторая строка
     */
    public static boolean alphabeticalOrderComparison(String first, String second) {
        return first.compareTo(second) < 0;
    }
}
