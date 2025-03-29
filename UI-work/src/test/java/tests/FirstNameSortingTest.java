package tests;

import helpers.CustomerDataUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersList;
import pages.MainPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;

public class FirstNameSortingTest extends BaseTest{

    @BeforeMethod
    void initBeforeMethod() {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
    }

    @Test(description = "Sorting by first name test")
    public void firstNameSortingTest() {
        MainPage mainPage = page(MainPage.class);
        CustomersList customersList = page(CustomersList.class);

        // Включение сортировки по имени
        mainPage.openCustomersList().firstNameSorting();

        // Получение первых двух имен
        List<String> firstTwoNames = customersList.getCustomersNames(2);
        String firstName1 = firstTwoNames.get(0);
        String firstName2 = firstTwoNames.get(1);

        // Проверка сортировки
        assertTrue(CustomerDataUtils.alphabeticalOrderComparison(firstName1, firstName2),
                "First name '" + firstName1 + "' is not alphabetically before second name '" + firstName2 + "'");
    }
}
