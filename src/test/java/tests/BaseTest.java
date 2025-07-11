package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Epic("Управление клиентами")
public class BaseTest {

    @BeforeClass
    public void initBeforeClass() {
        Configuration.remote = "http://selenoid:4444/wd/hub";
        Configuration.headless = true;
        Configuration.timeout = 3000;
    }

    @AfterClass
    public void tearDown() {
        try {
            Selenide.closeWebDriver();
        } catch (Exception e) {
            System.err.println("Ошибка при закрытии WebDriver: " + e.getMessage());
        }
    }
}
