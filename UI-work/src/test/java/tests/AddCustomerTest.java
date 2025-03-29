package tests;

import com.codeborne.selenide.ex.UIAssertionError;
import helpers.AlertHandler;
import helpers.AssertionsHelper;
import helpers.DataProviderHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CustomersList;
import pages.MainPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertFalse;

public class AddCustomerTest extends BaseTest{

    @BeforeMethod
    void initBeforeMethod() {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
    }

    @Test(description = "Add customer test", dataProvider = "customerData",
            dataProviderClass = DataProviderHelper.class)
    public void addCustomerTest(String postCode, String firstName) {
        MainPage mainPage = page(MainPage.class);
        CustomersList customersList = page(CustomersList.class);
        SoftAssert softAssert = new SoftAssert();

        // Создание клиента
        mainPage.openAddCustomerForm()
                .inputCustomerInfo(postCode, firstName, "doe")
                .submitForm();

        // Проверка уведомления - не является критической, поэтому SoftAssert
        String alertText = AlertHandler.getAlertText();
        AssertionsHelper.assertAlertText(softAssert, alertText, "Customer added successfully with customer id :");
        AlertHandler.acceptAlert();

        // Проверка наличия клиента
        mainPage.openCustomersList().inputSearchInfo(firstName);
        AssertionsHelper.assertCustomerExists(customersList, List.of(postCode, firstName, "doe"));

        // Удаление клиента
        customersList.deleteCustomer();
        AssertionsHelper.assertCustomerDeleted(customersList, List.of(postCode, firstName, "doe"));

        softAssert.assertAll();
    }

    @Test(description = "Negative test: Add customer with empty fields")
    public void negativeAddCustomerTest() {
        MainPage mainPage = page(MainPage.class);
        // Попытка создания клиента без заполнения полей
        mainPage.openAddCustomerForm()
                .inputCustomerInfo("", "", "")
                .submitForm();
        // Проверка, что уведомление не появилось
        boolean isAlertPresent = true;
        try {
            AlertHandler.getAlertText(); // Попытка получить текст алерта
        } catch (UIAssertionError e) {
            isAlertPresent = false;
        }
        assertFalse(isAlertPresent, "Alert is unexpectedly present!");
    }
}
