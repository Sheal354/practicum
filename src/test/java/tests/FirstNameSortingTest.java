package tests;

import helpers.CustomerDataUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersListPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;

@Feature("Фильтрация списка клиентов")
public class FirstNameSortingTest extends BaseTest{

    @BeforeMethod
    void initBeforeMethod() {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list");
    }

    @Story("Фильтрация клиентов по имени")
    @Description("Проверка фильтрации клиентов по имени в списке.")
    @Test(description = "Firstname Sorting Test")
    public void firstNameSortingTest() {
        CustomersListPage customersListPage = page(CustomersListPage.class);

        // Включение сортировки по имени
        customersListPage.firstNameSorting();

        // Получение первых двух имен
        List<String> firstTwoNames = customersListPage.getCustomersNames(2);
        String firstName1 = firstTwoNames.get(0);
        String firstName2 = firstTwoNames.get(1);

        // Проверка сортировки
        assertTrue(CustomerDataUtils.alphabeticalOrderComparison(firstName1, firstName2),
                "First name '" + firstName1 + "' is not alphabetically before second name '" + firstName2 + "'");
    }
}
