package helpers;

import java.util.List;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.CustomersList;


public class AssertionsHelper {

    /**
     * Проверка наличия клиента в таблице
     * @param customersList экземпляр класса CustomersList
     * @param customerInfo данные клиента: postCode, firstName, lastName
     */
    public static void assertCustomerExists(CustomersList customersList, List<String> customerInfo) {
        Assert.assertTrue(customersList.isRowExists(customerInfo.get(2), customerInfo.get(0), customerInfo.get(1)),
                "Customer not found!");
    }

    /**
     * Проверка удаления клиента
     * @param customersList экземпляр класса CustomersList
     * @param customerInfo данные клиента: postCode, firstName, lastName
     */
    public static void assertCustomerDeleted(CustomersList customersList, List<String> customerInfo) {
        Assert.assertFalse(customersList.isRowExists(customerInfo.get(2), customerInfo.get(0), customerInfo.get(1)),
                "Customer not deleted!");
    }

    /**
     * Проверка текста уведомления с использованием SoftAssert
     * @param softAssert экземпляр SoftAssert
     * @param alertText текст уведомления
     * @param expectedSubstring ожидаемый текст
     */
    public static void assertAlertText(SoftAssert softAssert, String alertText, String expectedSubstring) {
        softAssert.assertTrue(alertText.contains(expectedSubstring),
                "Alert text does not contain the expected substring: " + alertText);
    }
}
