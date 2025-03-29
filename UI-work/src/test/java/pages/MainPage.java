package pages;

import io.qameta.allure.Step;
import pages.elements.MenuButtons;

import static com.codeborne.selenide.Selenide.page;

/**
 * Класс главной страницы.
 */
public class MainPage {

    private final MenuButtons menuButtons = new MenuButtons();

    /**
     * Открытие формы создания клиента.
     * @return экземпляр класса AddCustomerForm.
     */
    @Step("Открытие формы создания клиента")
    public AddCustomerForm openAddCustomerForm() {
        menuButtons.addCustomerButtonClick();
        return page(AddCustomerForm.class);
    }

    /**
     * Открытие таблицы клиентов.
     * @return экземпляр класса CustomersList.
     */
    @Step("Открытие таблицы клиентов")
    public CustomersList openCustomersList() {
        menuButtons.customersButtonClick();
        return page(CustomersList.class);
    }
}
