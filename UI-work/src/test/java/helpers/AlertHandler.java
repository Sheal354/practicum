package helpers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Класс для управления уведомлениями
 */
public class AlertHandler {

    /**
     * Получение текста уведомления
     * @return текст уведомления
     */
    @Step("Получение текста уведомления")
    public static String getAlertText() {
        return switchTo().alert().getText();
    }

    /**
     * Закрытие уведомления
     */
    @Step("Закрытие уведомления")
    public static void acceptAlert() {
        switchTo().alert().accept();
    }
}
