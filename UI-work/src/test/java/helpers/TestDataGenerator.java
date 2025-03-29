package helpers;

import java.util.Random;

/**
 * Класс для генерации тестовых данных
 */
public class TestDataGenerator {

    /**
     * Генерация Post Code из 10 случайных цифр.
     * @return строка, представляющая Post Code из 10 цифр.
     */
    public static String generatePostCode() {
        Random random = new Random();
        StringBuilder postCode = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            postCode.append(random.nextInt(10)); // Генерация цифры от 0 до 9
        }
        return postCode.toString();
    }

    /**
     * Генерация First Name на основе Post Code.
     * @param postCode строка, представляющая Post Code из 10 цифр.
     * @return строка, представляющая First Name.
     */
    public static String generateFirstName(String postCode) {
        StringBuilder firstName = new StringBuilder();
        for (int i = 0; i < postCode.length(); i += 2) {
            int number = Integer.parseInt(postCode.substring(i, i + 2));
            char letter = (char) ('a' + (number % 26));
            firstName.append(letter);
        }
        return firstName.toString();
    }
}
