package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;


public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = FIREFOX;

        if (System.getProperty("remote.browser.url") != null){
            if (System.getProperty("remote.browser.url").equals("notebook.home")){
                Configuration.remote = "http://notebook.home:4444/wd/hub/";
            }
            else if (System.getProperty("remote.browser.url").equals("selenoid.autotests.cloud")) {
                Configuration.remote = "https://user1:1234@" + System.getProperty("remote.browser.url") + ":4444/wd/hub/";
            }
            else if (System.getProperty("remote.browser.url").equals("168.119.167.132")) {
                Configuration.remote = "https://" + System.getProperty("remote.browser.url") + ":4444/wd/hub/";
            }
        }
        Configuration.startMaximized = true;
    }

    @AfterEach
    public void wrapUp() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachVideo();
        closeWebDriver();
        }

}
