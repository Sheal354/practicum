package tests;

import com.codeborne.selenide.ex.UIAssertionError;
import helpers.AlertHandler;
import helpers.AssertionsHelper;
import helpers.DataProviderHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerFormPage;
import pages.CustomersListPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertFalse;

@Epic("Управление клиентами")
public class AddCustomerTest extends BaseTest{

    @BeforeMethod
    void initBeforeMethod() {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust");
    }

    @Feature("Создание клиента")
    @Story("Создание нового клиента с корректными данными")
    @Description("Проверка успешного создания клиента со сгенерированными данными.")
    @Test(dataProvider = "customerData", dataProviderClass = DataProviderHelper.class)
    public void addCustomerTest(String postCode, String firstName) {
        AddCustomerFormPage addCustomerFormPage = page(AddCustomerFormPage.class);
        CustomersListPage customersListPage = page(CustomersListPage.class);
        SoftAssert softAssert = new SoftAssert();

        // Создание клиента
        addCustomerFormPage.inputCustomerInfo(postCode, firstName, "doe")
                .submitForm();

        // Проверка уведомления - не является критической, поэтому SoftAssert
        String alertText = AlertHandler.getAlertText();
        AssertionsHelper.assertAlertText(softAssert, alertText, "Customer added successfully with customer id :");
        AlertHandler.acceptAlert();

        // Проверка наличия клиента
        addCustomerFormPage.openCustomersList().inputSearchInfo(firstName);
        AssertionsHelper.assertCustomerExists(customersListPage, List.of(postCode, firstName, "doe"));

        // Удаление клиента
        customersListPage.deleteCustomer();
        AssertionsHelper.assertCustomerDeleted(customersListPage, List.of(postCode, firstName, "doe"));

        softAssert.assertAll();
    }

    @Feature("Создание клиента")
    @Story("Создание нового клиента с некорректными данными")
    @Description("Проверка, что система не позволяет создать клиента с невалидными данными.")
    @Test
    public void negativeAddCustomerTest() {
        AddCustomerFormPage addCustomerFormPage = page(AddCustomerFormPage.class);
        // Попытка создания клиента без заполнения полей
        addCustomerFormPage.inputCustomerInfo("", "", "")
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
