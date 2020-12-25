package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

/**
 *
 */
public class ToolsQaPracticeFormTests extends TestBase{


    @Test
    @Tag("regForm")
    @DisplayName("ToolsQA reg form test")
    void regForm() {

        Faker faker = new Faker(new Locale("en-US"));
        FakeValuesService fakerValServ = new FakeValuesService(new Locale("en-US"), new RandomService());

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.cat().name().replace(" ", "") + "@" + faker.cat().breed().replace(" ", "") + ".com";
        String userNumber = fakerValServ.regexify("[0-9]{10}");
        String userGender = faker.options().option("Male", "Female", "Other");
        String dateOfBirthMonth = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        String dateOfBirthYear = "" + faker.number().numberBetween(1900, 2020);
        String dateOfBirthDay = "" + faker.number().numberBetween(1, 28);
        String address = faker.address().fullAddress();

        String  subject1 = "Chemistry",
                subject2 = "English",
                hobbie1 = "Sports",
                hobbie2 = "Reading",
                state = "Uttar Pradesh",city = "Lucknow";

        File file = new File("src/test/resources/pic2.jpg");
        String filename = file.getName();

        step("Filling out the form",() -> {
            open("https://demoqa.com/automation-practice-form");
// Filling out the form
            $("#firstName").val(firstName);
            $("#lastName").val(lastName);
            $("#userEmail").val(userEmail);
            $("#genterWrapper").$(byText(userGender)).click();
            $("#userNumber").val(userNumber);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(dateOfBirthMonth);
            $(".react-datepicker__year-select").selectOption(dateOfBirthYear);
            $(".react-datepicker__month").$(byText(dateOfBirthDay)).click();
            $("#subjectsInput").val("m");
            $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
            $("#subjectsInput").val("e");
            $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
            $("#hobbiesWrapper").$(byText(hobbie1)).click();
            $("#hobbiesWrapper").$(byText(hobbie2)).click();
            $("#uploadPicture").uploadFile(file);
            $("#currentAddress").val(address);
            $("#stateCity-wrapper").$("#state").scrollIntoView(true).click();
            $("#stateCity-wrapper").$("#state").$(byText(state)).click();
            $("#stateCity-wrapper").$("#city").click();
            $("#stateCity-wrapper").$("#city").$(byText(city)).click();
            $("#submit").click();
        });

        step("Checking that the result form contains all values that were filled.", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[1]").shouldHave(text("Student Name"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]").shouldHave(text(firstName + " " + lastName));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[1]").shouldHave(text("Student Email"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[2]").shouldHave(text(userEmail));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[3]/td[1]").shouldHave(text("Gender"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[3]/td[2]").shouldHave(text(userGender));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td[1]").shouldHave(text("Mobile"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td[2]").shouldHave(text(userNumber));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td[1]").shouldHave(text("Date of Birth"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td[2]").shouldHave(text(dateOfBirthDay + " " + dateOfBirthMonth +"," + dateOfBirthYear));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[6]/td[1]").shouldHave(text("Subjects"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[6]/td[2]").shouldHave(text(subject1 + ", " + subject2));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[7]/td[1]").shouldHave(text("Hobbies"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[7]/td[2]").shouldHave(text(hobbie1 + ", " + hobbie2));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[8]/td[1]").shouldHave(text("Picture"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[8]/td[2]").shouldHave(text(filename));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[9]/td[1]").shouldHave(text("Address"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[9]/td[2]").shouldHave(text(address));

            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[10]/td[1]").shouldHave(text("State and City"));
            $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[10]/td[2]").shouldHave(text(state + " " + city));
        });
    }
}
