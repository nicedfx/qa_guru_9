package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

/**
 *
 */
public class AlfaBankTests extends TestBase{

    @Test
    public void alfaTestDeposits() {
        step("Navigate to the sought for page.", () -> {
            open("https://alfabank.ru");
            $("#alfa").$(byText("Вклады")).click();
            $("#filter").$$(byText("Депозиты")).find(visible).parent().click();
            $("#more-buttons").$(byText("Архивные счета и депозиты")).click();
            $("#filter").$(byText("Депозиты")).click();
        });
        step("Check that the page contains only 5 archive deposits.", () -> {
            $("#alfa-account").parent().$$("[data-widget-name=CatalogCard").shouldHaveSize(5);
        });
    }

    @Test
    public void alfaTestSibling() {
        step("Using a simple selector with \"sibling\" method", () -> {
            open("https://alfabank.ru/make-money/");
            $("[data-test-id=tabs-list-tabTitle-0]").sibling(0).scrollIntoView(true).click();
            $("[data-test-id=accordion-header-0]").shouldHave(text("Альфа-Банк является участником системы обязательного страхования вкладов"));
        });
    }

    @Test
    public void alfaTestPrecedingAndParent() {
        step("Using a simple selector with \"preceding\" method", () -> {
            open("https://alfabank.ru/make-money/");
            $("[data-test-id=tabs-list-tabTitle-2]").preceding(0).scrollIntoView(true).click();
            $("[data-test-id=accordion-item-0]").parent().shouldHave(text("Страхованию подлежат"));
        });
    }

    @Test
    public void alfaTestClosest() {
        step("Using a simple selector with \"preceding\" method", () -> {
            open("https://alfabank.ru/make-money/");
            $("[data-test-id=tabs-list-tabTitle-2]").preceding(0).scrollIntoView(true).click();
            $("[data-test-id=accordion-item-1]").closest(".m23QzrO").shouldHave(text("Как происходит возмещение средств?"));
        });
    }
}
