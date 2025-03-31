package helpers;

import org.testng.annotations.DataProvider;

/**
 * Класс для предоставления тестовых данных
 */
public class DataProviderHelper {

    /**
     * Количество тестовых данных
     */
    private static int numberOfDataSets = 3;

    /**
     * Установка количества тестовых данных
     * @param value количество тестовых данных
     */
    public static void setNumberOfDataSets(int value) {
        numberOfDataSets = value;
    }

    /**
     * Метод для предоставления тестовых данных.
     * @return двумерный массив с тестовыми данными.
     */
    @DataProvider(name = "customerData")
    public static Object[][] provideCustomerData() {
        Object[][] data = new Object[numberOfDataSets][2];

        for (int i = 0; i < numberOfDataSets; i++) {
            data[i][0] = TestDataGenerator.generatePostCode();
            data[i][1] = TestDataGenerator.generateFirstName(data[i][0].toString());
        }
        return data;
    }
}