package tests;

import helpers.AssertionsHelper;
import helpers.CustomerDataUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersListPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@Epic("Управление клиентами")
public class DeleteCustomerTest {

    private CustomersListPage customersListPage;
    private List<String> customerInfo; // Информация о клиенте для воссоздания

    @BeforeMethod
    void initBeforeMethod() {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list");
        customersListPage = page(CustomersListPage.class);
    }

    @Feature("Удаление клиента")
    @Story("Удаление существующего клиента")
    @Description("Проверка успешного удаления существующего клиента из системы.")
    @Test(description = "Delete Customer Test")
    public void deleteCustomerTest() {
        // Найти имя ближайшее к среднему
        List<String> customersNames = customersListPage.getCustomersNames(-1);
        double averageLength = CustomerDataUtils.calculateAverageLength(customersNames);
        String closestName = CustomerDataUtils.findClosestName(customersNames, averageLength);

        // Поиск клиента и удаление
        customersListPage.inputSearchInfo(closestName);
        customerInfo = customersListPage.getRowData(); // Сохраняем информацию о клиенте
        customersListPage.deleteCustomer();

        // Проверка удаления
        AssertionsHelper.assertCustomerDeleted(customersListPage, customerInfo);
    }

    @AfterMethod
    void restoreCustomer() {
        if (customerInfo != null) {
            // Воссоздание клиента
            customersListPage.openAddCustomerForm()
                    .inputCustomerInfo(customerInfo.get(2), customerInfo.get(0), customerInfo.get(1))
                    .submitForm()
                    .openCustomersList()
                    .inputSearchInfo(customerInfo.get(0));

            // Проверка наличия клиента
            AssertionsHelper.assertCustomerExists(customersListPage, customerInfo);
        }
    }
}