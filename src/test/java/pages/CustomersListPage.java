package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.ArrayList;
import java.util.List;
import io.qameta.allure.Step;
import pages.elements.MenuButtonsElements;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс таблицы клиентов
 */
public class CustomersListPage {

    /**
     * Поле ввода для поиска клиента.
     */
    private final SelenideElement searchCustomerInput = $("[ng-model='searchCustomer']");

    /**
     * Кнопка delete.
     */
    private final SelenideElement deleteCustomerButton = $("[ng-click='deleteCust(cust)']");

    /**
     * Фильтр записей клиентов по имени.
     */
    private final SelenideElement firstNameFilter = $x("//a[contains(@ng-click, \"sortType = 'fName'\")]");

    /**
     * Первая строка данных клиента из таблицы.
     */
    SelenideElement rowCustomerData = $$("tbody tr").first();

    private final MenuButtonsElements menuButtonsElements = new MenuButtonsElements();

    /**
     * Ввод данных в поисковик
     * @param firstName строка, представляющая имя клиента.
     * @return текущий экземпляр класса
     */
    @Step("Поиск клиента в таблице")
    public CustomersListPage inputSearchInfo(String firstName) {
        searchCustomerInput
                .shouldBe(visible)
                .sendKeys(firstName);
        return this;
    }

    /**
     * Проверка наличия записи клиента в таблице
     * @param postCode строка, представляющая Post Code клиента.
     * @param firstName строка, представляющая имя клиента.
     * @param lastName строка, представляющая фамилию клиента.
     */
    @Step("Проверка наличия записи клиента в таблице")
    public boolean isRowExists(String postCode, String firstName, String lastName) {
        String xpath = "//tr[td[text()='" + firstName + "'] " +
                "and td[text()='" + lastName + "'] " +
                "and td[text()='" + postCode + "']]";
        return $x(xpath).isDisplayed();
    }

    @Step("Удаление клиента из таблицы")
    public CustomersListPage deleteCustomer() {
        deleteCustomerButton
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Сортировка записей таблицы клиентов по имени")
    public CustomersListPage firstNameSorting() {
        firstNameFilter
                .shouldBe(visible)
                .doubleClick();
        return this;
    }

    /**
     * Получение имен из колонки "First Name".
     * @param limit количество записей, где -1 означает получение всех записей
     */
    public List<String> getCustomersNames(int limit) {
        rowCustomerData.shouldBe(visible);
        ElementsCollection rows = $$("tbody tr");
        return rows.stream()
                .limit(limit > 0 ? limit : Long.MAX_VALUE) // Берем только первые две строки
                .map(row -> row.$("td").getText())
                .toList();
    }

    public List<String> getRowData() {
        ElementsCollection cells = rowCustomerData.$$("td");
        List<String> rowData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SelenideElement cell = cells.get(i);
            rowData.add(cell.getText().trim());
        }
        return rowData;
    }

    @Step("Открытие формы создания клиента")
    public AddCustomerFormPage openAddCustomerForm() {
        menuButtonsElements.addCustomerButtonClick();
        return page(AddCustomerFormPage.class);
    }
}
