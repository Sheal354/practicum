package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void initBeforeClass() {
        Configuration.browserSize = "1920x2100";
        Configuration.timeout = 5000;
    }
}
