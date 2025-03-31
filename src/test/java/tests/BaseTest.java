package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void initBeforeClass() {
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
