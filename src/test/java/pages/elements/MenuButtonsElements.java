package pages.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс кнопок меню.
 */
public class MenuButtonsElements {

    /**
     * Кнопка Add Customer.
     */
    private final SelenideElement addCustomerButton = $("[ng-click='addCust()']");

    /**
     * Кнопка Customers.
     */
    private final SelenideElement customersButton = $("[ng-click='showCust()']");


    /**
     * Нажатие на кнопку Add Customer.
     */
    public void addCustomerButtonClick() {
        addCustomerButton
                .shouldBe(visible)
                .click();
    }

    /**
     * Нажатие на кнопку Customers.
     */
    public void customersButtonClick() {
        customersButton
                .shouldBe(visible)
                .click();
    }
}

