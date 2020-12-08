package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 *
 */
public class AlfaBankTests {

    @Test
    public void alfaTestDeposits() {
        open("https://alfabank.ru");
//      Navigate to the sought for page.
        $("#alfa").$(byText("Вклады")).click();
        $("#filter").$$(byText("Депозиты")).find(visible).parent().click();
        $("#more-buttons").$(byText("Архивные счета и депозиты")).click();
        $("#filter").$(byText("Депозиты")).click();
//      Check that the page contains only 5 archive deposits.
        $("#alfa-account").parent().$$("[data-widget-name=CatalogCard").shouldHaveSize(5);
    }

    @Test
    public void alfaTestSibling() {
        open("https://alfabank.ru/make-money/");
        $("[data-test-id=tabs-list-tabTitle-0]").sibling(0).scrollIntoView(true).click();
        $("[data-test-id=accordion-header-0]").shouldHave(text("Альфа-Банк является участником системы обязательного страхования вкладов"));
//        sleep(20000);
    }

    @Test
    public void alfaTestPrecedingAndParent() {
        open("https://alfabank.ru/make-money/");
        $("[data-test-id=tabs-list-tabTitle-2]").preceding(0).scrollIntoView(true).click();
        $("[data-test-id=accordion-item-0]").parent().shouldHave(text("Страхованию подлежат"));
    }

    @Test
    public void alfaTestClosest() {
        open("https://alfabank.ru/make-money/");
        $("[data-test-id=tabs-list-tabTitle-2]").preceding(0).scrollIntoView(true).click();
        $("[data-test-id=accordion-item-1]").closest(".m23QzrO").shouldHave(text("Как происходит возмещение средств?"));
    }
}
