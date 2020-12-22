package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;


public class TestBase {
    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
//        Configuration.remote = "http://" + System.getProperty("remote.browser.url") + ":4444/wd/hub/";
        Configuration.remote = "http://notebook.home:4444/wd/hub/";
//        System.out.println("!@#!@#!@#@# SELENOID URI IS: " + Configuration.remote);
        Configuration.startMaximized = true;
    }

    @AfterEach
    public void wrapUp() {

        Selenide.closeWebDriver();
        }

}
