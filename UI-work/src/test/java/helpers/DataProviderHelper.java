package helpers;

import org.testng.annotations.DataProvider;

/**
 * Класс для предоставления тестовых данных
 */
public class DataProviderHelper {

    /**
     * Метод для предоставления тестовых данных.
     * @return двумерный массив с тестовыми данными.
     */
    @DataProvider(name = "customerData")
    public static Object[][] provideCustomerData() {
        // Количество наборов данных
        int numberOfDataSets = 3;
        Object[][] data = new Object[numberOfDataSets][2];

        for (int i = 0; i < numberOfDataSets; i++) {
            String postCode = TestDataGenerator.generatePostCode();
            String firstName = TestDataGenerator.generateFirstName(postCode);
            data[i][0] = postCode;
            data[i][1] = firstName;
        }
        return data;
    }
}
