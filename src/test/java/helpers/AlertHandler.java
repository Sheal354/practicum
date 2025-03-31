package helpers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Класс для управления уведомлениями
 */
public class AlertHandler {

    @Step("Получение текста уведомления")
    public static String getAlertText() {
        return switchTo().alert().getText();
    }

    @Step("Закрытие уведомления")
    public static void acceptAlert() {
        switchTo().alert().accept();
    }
}
