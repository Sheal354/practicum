package tests;

import helpers.AssertionsHelper;
import helpers.CustomerDataUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersList;
import pages.MainPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class DeleteCustomerTest {

    @BeforeMethod
    void initBeforeMethod() {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
    }

    @Test(description = "Delete customer test")
    public void deleteCustomerTest() {
        MainPage mainPage = page(MainPage.class);
        CustomersList customersList = page(CustomersList.class);

        // Открыть список клиентов
        mainPage.openCustomersList();

        // Найти имя ближайшее к среднему
        List<String> customersNames = customersList.getCustomersNames(-1);
        double averageLength = CustomerDataUtils.calculateAverageLength(customersNames);
        String closestName = CustomerDataUtils.findClosestName(customersNames, averageLength);

        // Поиск клиента и удаление
        customersList.inputSearchInfo(closestName);
        List<String> customerInfo = customersList.getRowData();
        customersList.deleteCustomer();

        // Проверка удаления
        AssertionsHelper.assertCustomerDeleted(customersList, customerInfo);

        // Воссоздание клиента
        mainPage.openAddCustomerForm()
                .inputCustomerInfo(customerInfo.get(2), customerInfo.get(0), customerInfo.get(1))
                .submitForm()
                .openCustomersList()
                .inputSearchInfo(customerInfo.get(0));

        // Проверка наличия клиента
        AssertionsHelper.assertCustomerExists(customersList, customerInfo);
    }
}
