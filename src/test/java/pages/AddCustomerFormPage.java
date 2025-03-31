package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.elements.MenuButtonsElements;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс формы создания клиента
 */
public class AddCustomerFormPage {

    /**
     * Поле ввода First Name.
     */
    private final SelenideElement firstNameInput = $("[ng-model='fName']");

    /**
     * Поле ввода Last Name.
     */
    private final SelenideElement lastNameInput = $("[ng-model='lName']");

    /**
     * Поле ввода Post Code.
     */
    private final SelenideElement postCodeInput = $("[ng-model='postCd']");

    /**
     * Кнопка Add Customer.
     */
    private final SelenideElement addCustomerButton = $x("//button[@type='submit' " +
            "and contains(text(),'Add Customer')]");

    private final MenuButtonsElements menuButtonsElements = new MenuButtonsElements();

    /**
     * Ввод данных в поля ввода
     * @param postCode строка, представляющая Post Code клиента.
     * @param firstName строка, представляющая имя клиента.
     * @param lastName строка, представляющая фамилию клиента.
     * @return текущий экземпляр класса
     */
    @Step("Заполнение полей формы")
    public AddCustomerFormPage inputCustomerInfo(String postCode, String firstName, String lastName) {
        postCodeInput
                .shouldBe(visible)
                .sendKeys(postCode);
        firstNameInput
                .shouldBe(visible)
                .sendKeys(firstName);
        lastNameInput
                .shouldBe(visible)
                .sendKeys(lastName);
        return this;
    }

    @Step("Подтверждение заполнения формы")
    public AddCustomerFormPage submitForm() {
        addCustomerButton
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Открытие таблицы клиентов")
    public CustomersListPage openCustomersList() {
        menuButtonsElements.customersButtonClick();
        return page(CustomersListPage.class);
    }

}
