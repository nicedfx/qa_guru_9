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
import static org.openqa.selenium.remote.BrowserType.EDGE;


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
            else {
                Configuration.remote = "https://" + System.getProperty("remote.browser.url") + ":4444/wd/hub/";
            }
        }
//        else {
//            Configuration.remote = "http://notebook.home:4444/wd/hub/";
//        }

//        System.out.println("!@#!@#!@#@# SELENOID URI IS: " + Configuration.remote);
        Configuration.startMaximized = true;
    }

    @AfterEach
    public void wrapUp() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachVideo();
//        attachAsText("Browser console logs", getConsoleLogs());
//        attachAsText("Browser console logs", "Just a text");
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        System.out.println("Printing out the console logs");
//        System.out.println(Selenide.getWebDriverLogs(BROWSER));
//        System.out.println(getConsoleLogs());
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        closeWebDriver();
        }

}
